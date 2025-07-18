package kr.mywork.domain.project.service;

import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.activityLog.listener.eventObject.ActivityLogCreateEvent;
import kr.mywork.domain.activityLog.listener.eventObject.ActivityLogDeleteEvent;
import kr.mywork.domain.activityLog.listener.eventObject.ActivityModifyEvent;
import kr.mywork.domain.company.errors.CompanyErrorType;
import kr.mywork.domain.company.errors.CompanyNotFoundException;
import kr.mywork.domain.company.model.Company;
import kr.mywork.domain.company.model.CompanyType;
import kr.mywork.domain.company.repository.CompanyRepository;
import kr.mywork.domain.member.errors.MemberErrorType;
import kr.mywork.domain.member.errors.MemberTypeNotFoundException;
import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.model.MemberRole;
import kr.mywork.domain.member.repository.MemberRepository;
import kr.mywork.domain.member.service.dto.response.MemberProjectInfoResponse;
import kr.mywork.domain.post.repository.PostRepository;
import kr.mywork.domain.project.errors.ProjectAssignNotFoundException;
import kr.mywork.domain.project.errors.ProjectErrorType;
import kr.mywork.domain.project.errors.ProjectNotFoundException;
import kr.mywork.domain.project.model.Project;
import kr.mywork.domain.project.model.ProjectAssign;
import kr.mywork.domain.project.repository.ProjectAssignRepository;
import kr.mywork.domain.project.repository.ProjectRepository;
import kr.mywork.domain.project.service.dto.request.ProjectCreateRequest;
import kr.mywork.domain.project.service.dto.request.ProjectUpdateRequest;
import kr.mywork.domain.project.service.dto.response.*;
import kr.mywork.domain.project_member.repository.ProjectMemberRepository;
import kr.mywork.interfaces.project.controller.dto.response.ProjectStatusUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

	private static final String CLIENT_COMPANY_NAME = "CLIENT_COMPANY_NAME";
	private static final String DEV_COMPANY_NAME = "DEV_COMPANY_NAME";
	private static final String PROJECT_NAME = "PROJECT_NAME";

	@Value("${project.page.size}")
	private int projectPageSize;

	private final ProjectRepository projectRepository;
	private final ProjectAssignRepository projectAssignRepository;
	private final CompanyRepository companyRepository;
	private final MemberRepository memberRepository;
	private final PostRepository postRepository;
	private final ProjectMemberRepository projectMemberRepository;
	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public UUID createProject(ProjectCreateRequest request, LoginMemberDetail loginMemberDetail) {

		final Project savedProject = projectRepository.save(
			new Project(request.name(), request.startAt(), request.endAt(), request.step(), request.detail(),request.projectAmount()));

		projectAssignRepository.save(
			new ProjectAssign(savedProject.getId(), request.devCompanyId(), request.clientCompanyId()));

		eventPublisher.publishEvent(new ActivityLogCreateEvent(savedProject, loginMemberDetail));

		return savedProject.getId();
	}

	@Transactional
	public UUID deleteProject(UUID projectId, LoginMemberDetail loginMemberDetail) {
		var project = projectRepository.findById(projectId)
			.orElseThrow(() -> new ProjectNotFoundException(ProjectErrorType.PROJECT_NOT_FOUND));

		project.setDeleted(true);

		eventPublisher.publishEvent(new ActivityLogDeleteEvent(project, loginMemberDetail));

		return project.getId();
	}

	@Transactional
	public ProjectUpdateResponse updateProject(UUID projectId, ProjectUpdateRequest request, LoginMemberDetail loginMemberDetail) {
		var project = projectRepository.findById(projectId)
			.orElseThrow(() -> new ProjectNotFoundException(ProjectErrorType.PROJECT_NOT_FOUND));

		Project before = Project.copyOf(project);

		project.updateFrom(request);

		eventPublisher.publishEvent(new ActivityModifyEvent(before, project, loginMemberDetail));

		return ProjectUpdateResponse.from(project);
	}

	@Transactional(readOnly = true)
	public ProjectDetailResponse findProjectDetailsById(UUID projectId) {
		final Project project = projectRepository.findById(projectId)
			.orElseThrow(() -> new ProjectNotFoundException(ProjectErrorType.PROJECT_NOT_FOUND));

		final ProjectAssign projectAssign = projectAssignRepository.findByProjectId(projectId)
			.orElseThrow(() -> new ProjectAssignNotFoundException(ProjectErrorType.PROJECT_ASSIGN_NOT_FOUND));

		final Company devCompany = companyRepository.findById(projectAssign.getDevCompanyId())
			.orElseThrow(() -> new CompanyNotFoundException(CompanyErrorType.COMPANY_NOT_FOUND));

		final Company clientCompany = companyRepository.findById(projectAssign.getClientCompanyId())
			.orElseThrow(() -> new CompanyNotFoundException(CompanyErrorType.COMPANY_NOT_FOUND));

		return new ProjectDetailResponse(project.getId(), project.getName(), project.getStartAt(), project.getEndAt(),
			project.getStep(), project.getDetail(), project.getDeleted(), project.getCreatedAt(), project.getProjectAmount(),
			devCompany.getId(), devCompany.getName(), devCompany.getContactPhoneNumber(),
			clientCompany.getId(), clientCompany.getName(),clientCompany.getContactPhoneNumber());
	}

	@Transactional(readOnly = true)
	public List<ProjectSelectResponse> findProjectsBySearchConditionWithPaging(
		final String keywordType, final String keyword, final String step, final Integer page) {
		if (keywordType == null) {
			final List<Project> projects =
				projectRepository.findAllByStepAndNameWithPaging(step, null, page, projectPageSize);

			final List<UUID> projectIds = projects.stream().map(Project::getId).toList();

			final Map<UUID, ProjectAssign> projectIdAssignMap =
				transformProjectAssignMap(projectAssignRepository.findAllByProjectIds(projectIds));

			final List<UUID> devCompanyIds = projectIdAssignMap.values()
				.stream()
				.map(ProjectAssign::getDevCompanyId)
				.toList();

			final List<UUID> clientCompanyIds = projectIdAssignMap.values()
				.stream().map(ProjectAssign::getClientCompanyId).toList();

			final Map<UUID, Company> devCompanyMap = transformCompanyMap(companyRepository.findAllByIds(devCompanyIds));
			final Map<UUID, Company> clientCompanyMap = transformCompanyMap(
				companyRepository.findAllByIds(clientCompanyIds));

			return transformProjectSelectResponses(projects, projectIdAssignMap, devCompanyMap, clientCompanyMap);

		}

		switch (keywordType) {
			case DEV_COMPANY_NAME -> {
				final Map<UUID, Company> devCompanyMap =
					transformCompanyMap(companyRepository.findAllByNameAndType(keyword, CompanyType.DEV.name()));

				final List<ProjectAssign> devCompanyProjectAssigns = projectAssignRepository.findAllByCompanyIdsAndType(
					devCompanyMap.keySet(), CompanyType.DEV.name());

				final Map<UUID, ProjectAssign> projectIdAssignMap = transformProjectAssignMap(devCompanyProjectAssigns);
				final List<Project> projects = projectRepository.findAllByIdsAndStep(projectIdAssignMap.keySet(), step,
					page, projectPageSize);

				projects.sort(Comparator.comparing(Project::getId));

				List<UUID> clientCompanyIds = projects.stream()
					.map(project -> {
						final ProjectAssign projectAssign = projectIdAssignMap.get(project.getId());
						return projectAssign.getClientCompanyId();
					}).toList();

				final Map<UUID, Company> clientCompanyMap =
					transformCompanyMap(companyRepository.findAllByIds(clientCompanyIds));

				return transformProjectSelectResponses(projects, projectIdAssignMap, devCompanyMap, clientCompanyMap);
			}

			case CLIENT_COMPANY_NAME -> {
				final Map<UUID, Company> clientCompanyMap =
					transformCompanyMap(companyRepository.findAllByNameAndType(keyword, CompanyType.CLIENT.name()));

				final List<ProjectAssign> clientCompanyProjectAssigns = projectAssignRepository.findAllByCompanyIdsAndType(
					clientCompanyMap.keySet(), CompanyType.CLIENT.name());

				final Map<UUID, ProjectAssign> projectIdAssignMap =
					transformProjectAssignMap(clientCompanyProjectAssigns);

				final List<Project> projects =
					projectRepository.findAllByIdsAndStep(projectIdAssignMap.keySet(), step, page, projectPageSize);

				projects.sort(Comparator.comparing(Project::getId));

				List<UUID> devCompanyIds = projects.stream()
					.map(project -> {
						final ProjectAssign projectAssign = projectIdAssignMap.get(project.getId());
						return projectAssign.getDevCompanyId();
					}).toList();

				final Map<UUID, Company> devCompanyMap = transformCompanyMap(
					companyRepository.findAllByIds(devCompanyIds));

				return transformProjectSelectResponses(projects, projectIdAssignMap, devCompanyMap, clientCompanyMap);
			}

			case PROJECT_NAME -> {
				final List<Project> projects = projectRepository.findAllByStepAndNameWithPaging(step,
					keyword, page, projectPageSize);

				projects.sort(Comparator.comparing(Project::getId));

				final List<UUID> projectIds = projects.stream().map(Project::getId).collect(Collectors.toList());
				final List<ProjectAssign> projectAssigns = projectAssignRepository.findAllByProjectIds(projectIds);
				final List<UUID> devCompanyIds = projectAssigns.stream().map(ProjectAssign::getDevCompanyId).toList();
				final List<UUID> clientCompanyIds = projectAssigns.stream()
					.map(ProjectAssign::getClientCompanyId)
					.toList();

				final Map<UUID, ProjectAssign> projectIdAssignMap =
					transformProjectAssignMap(projectAssignRepository.findAllByProjectIds(projectIds));
				final Map<UUID, Company> devCompanyMap = transformCompanyMap(
					companyRepository.findAllByIds(devCompanyIds));
				final Map<UUID, Company> clientCompanyMap = transformCompanyMap(
					companyRepository.findAllByIds(clientCompanyIds));

				return transformProjectSelectResponses(projects, projectIdAssignMap, devCompanyMap, clientCompanyMap);
			}
		}

		throw new IllegalStateException();
	}

	private List<ProjectSelectResponse> transformProjectSelectResponses(
		final List<Project> projects,
		final Map<UUID, ProjectAssign> projectIdAssignMap,
		final Map<UUID, Company> devCompanyMap,
		final Map<UUID, Company> clientCompanyMap) {

		return projects.stream()
			.map(project -> {
				final ProjectAssign projectAssign = projectIdAssignMap.get(project.getId());
				final Company devCompany = devCompanyMap.get(projectAssign.getDevCompanyId());
				final Company clientCompany = clientCompanyMap.get(projectAssign.getClientCompanyId());

				return new ProjectSelectResponse(
					project.getId(), project.getName(), project.getStartAt(), project.getEndAt(), project.getStep(),
					devCompany.getId(), devCompany.getName(), clientCompany.getId(), clientCompany.getName());
			}).toList();
	}

	private Map<UUID, ProjectAssign> transformProjectAssignMap(final List<ProjectAssign> projectAssigns) {
		return projectAssigns.stream()
			.collect(Collectors.toMap(ProjectAssign::getProjectId, projectAssign -> projectAssign));
	}

	private Map<UUID, Company> transformCompanyMap(final List<Company> companies) {
		return companies.stream()
			.collect(Collectors.toMap(Company::getId, company -> company));
	}

	@Transactional(readOnly = true)
	public Long countTotalProjectsByCondition(final String keywordType, final String keyword, final String step) {
		if (PROJECT_NAME.equals(keywordType)) {
			return projectRepository.countTotalProjectsByNameAndStep(keyword, step);
		}

		if (DEV_COMPANY_NAME.equals(keywordType)) {
			final List<Company> devCompanies = companyRepository.findAllByNameAndType(keyword, CompanyType.DEV.name());
			final List<UUID> devCompanyIds = devCompanies.stream().map(Company::getId).toList();

			final List<ProjectAssign> projectAssigns =
				projectAssignRepository.findAllByCompanyIdsAndType(devCompanyIds, CompanyType.DEV.name());
			final List<UUID> projectAssignIds = projectAssigns.stream().map(ProjectAssign::getProjectId).toList();

			return projectRepository.countTotalProjectIdsAndStep(projectAssignIds, step);
		}

		if (CLIENT_COMPANY_NAME.equals(keywordType)) {
			final List<Company> clientCompanies =
				companyRepository.findAllByNameAndType(keyword, CompanyType.CLIENT.name());
			final List<UUID> clientCompanyIds = clientCompanies.stream().map(Company::getId).toList();

			final List<ProjectAssign> projectAssigns =
				projectAssignRepository.findAllByCompanyIdsAndType(clientCompanyIds, CompanyType.CLIENT.name());
			final List<UUID> projectAssignIds = projectAssigns.stream().map(ProjectAssign::getProjectId).toList();

			return projectRepository.countTotalProjectIdsAndStep(projectAssignIds, step);
		}

		return projectRepository.countTotalProjectIdsAndStep(null, step);
	}

	@Transactional
	public List<ProjectMemberResponse> findMemberByCompanyId(UUID companyId, UUID projectId) {

		List<Member> companyMembers = memberRepository.findMemberListByCompanyId(companyId, projectId);

		return companyMembers.stream()
			.map(member -> new ProjectMemberResponse(
				member.getId(),
				member.getName(),
				member.getEmail()
			)).collect(Collectors.toList());
	}

	@Transactional
	public List<MemberProjectInfoResponse> findProjectsAssignedMember(UUID memberId) {

		List<MemberProjectInfoResponse> memberProjectList = projectRepository.findeMemberProjectList(memberId);

		return memberProjectList;
	}

	@Transactional
	public List<MyProjectSelectResponse> findProjectsByLoginMember(LoginMemberDetail loginMemberDetail){
		String memberRole = loginMemberDetail.roleName();
		UUID companyId = loginMemberDetail.companyId();
		final List<Project> myProjects;

		// dev,client Admin
		if(MemberRole.CLIENT_ADMIN.isSameRoleName(memberRole) ||
				MemberRole.DEV_ADMIN.isSameRoleName(memberRole)){

			final List<UUID> projectIds = projectAssignRepository.findCompanyProjectsByCompanyId(companyId,memberRole);

			 myProjects =  projectRepository.findProjectsByIds(projectIds);

		// user
		}else if (MemberRole.USER.isSameRoleName(memberRole)){

			final List<UUID> projectIds = projectMemberRepository.findProjectIdsByMemberId(loginMemberDetail.memberId());

			myProjects =  projectRepository.findProjectsByIds(projectIds);

		}else{
			throw new MemberTypeNotFoundException(MemberErrorType.TYPE_NOT_FOUND);
		}

		return myProjects.stream()
				.map(project -> MyProjectSelectResponse.of(
						project.getId(),
						project.getName(),
						project.getDetail(),
						project.getStartAt(),
						project.getEndAt()
				))
				.toList();
	}
	@Transactional
	public ProjectStatusUpdateResponse updateProjectStatus(UUID projectId, String status){
		final Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new ProjectNotFoundException(ProjectErrorType.PROJECT_NOT_FOUND));

		project.updateStatus(status);

		return new  ProjectStatusUpdateResponse(project.getId(),project.getStep());
	}
}
