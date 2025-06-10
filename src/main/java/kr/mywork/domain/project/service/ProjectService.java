package kr.mywork.domain.project.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.domain.company.errors.CompanyErrorType;
import kr.mywork.domain.company.errors.CompanyNotFoundException;
import kr.mywork.domain.company.model.Company;
import kr.mywork.domain.company.repository.CompanyRepository;
import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.repository.MemberRepository;
import kr.mywork.domain.member.service.dto.response.MemberProjectInfoResponse;
import kr.mywork.domain.project.errors.ProjectAssignNotFoundException;
import kr.mywork.domain.project.errors.ProjectErrorType;
import kr.mywork.domain.project.errors.ProjectNotFoundException;
import kr.mywork.domain.project.model.Project;
import kr.mywork.domain.project.model.ProjectAssign;
import kr.mywork.domain.project.repository.ProjectAssignRepository;
import kr.mywork.domain.project.repository.ProjectRepository;
import kr.mywork.domain.project.service.dto.request.ProjectCreateRequest;
import kr.mywork.domain.project.service.dto.request.ProjectUpdateRequest;
import kr.mywork.domain.project.service.dto.response.ProjectDetailResponse;
import kr.mywork.domain.project.service.dto.response.ProjectMemberResponse;
import kr.mywork.domain.project.service.dto.response.ProjectSelectWithAssignResponse;
import kr.mywork.domain.project.service.dto.response.ProjectUpdateResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

	@Value("${project.page.size}")
	private int projectPageSize;

	private final ProjectRepository projectRepository;
	private final ProjectAssignRepository projectAssignRepository;
	private final CompanyRepository companyRepository;
	private final MemberRepository memberRepository;

	@Transactional
	public UUID createProject(ProjectCreateRequest request) {

		final Project savedProject = projectRepository.save(
			new Project(request.name(), request.startAt(), request.endAt(), request.step(), request.detail()));

		projectAssignRepository.save(
			new ProjectAssign(savedProject.getId(), request.devCompanyId(), request.clientCompanyId()));

		return savedProject.getId();
	}

	@Transactional
	public UUID deleteProject(UUID projectId) {
		var project = projectRepository.findById(projectId)
			.orElseThrow(() -> new ProjectNotFoundException(ProjectErrorType.PROJECT_NOT_FOUND));

		project.setDeleted(true);
		return project.getId();
	}

	@Transactional
	public ProjectUpdateResponse updateProject(UUID projectId, ProjectUpdateRequest request) {
		var project = projectRepository.findById(projectId)
			.orElseThrow(() -> new ProjectNotFoundException(ProjectErrorType.PROJECT_NOT_FOUND));

		project.updateFrom(request);
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
			project.getStep(), project.getDetail(), project.getDeleted(), project.getCreatedAt(), devCompany.getId(),
			devCompany.getName(), devCompany.getContactPhoneNumber(), clientCompany.getId(), clientCompany.getName(),
			clientCompany.getContactPhoneNumber());
	}

	@Transactional(readOnly = true)
	public List<ProjectSelectWithAssignResponse> findProjectsBySearchConditionWithPaging(
		final int page,
		final UUID memberId,
		final String nameKeyword,
		final Boolean deleted
	) {
		return projectRepository.findProjectsBySearchConditionWithPaging(
			page, projectPageSize, memberId, nameKeyword, deleted
		);
	}

	@Transactional(readOnly = true)
	public Long countTotalProjectsByCondition(
		final UUID memberId,
		final String nameKeyword,
		final Boolean deleted
	) {
		return projectRepository.countTotalProjectsByCondition(memberId, nameKeyword, deleted);
	}

	@Transactional
	public List<ProjectMemberResponse> findMemberByCompanyId(UUID companyId ,UUID projectId) {

		List<Member> companyMembers = memberRepository.findMemberListByCompanyId(companyId,projectId);

		return companyMembers.stream()
			.map(member -> new ProjectMemberResponse(
				member.getId(),
				member.getName()
			)).collect(Collectors.toList());
	}

	@Transactional
	public List<MemberProjectInfoResponse> findProjectsAssignedMember(UUID memberId) {

		List<MemberProjectInfoResponse> memberProjectList = projectRepository.findeMemberProjectList(memberId);

		return memberProjectList;
	}
}
