package kr.mywork.domain.project.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.repository.MemberRepository;
import kr.mywork.domain.member.service.dto.response.MemberProjectInfoResponse;
import kr.mywork.domain.project.errors.ProjectErrorType;
import kr.mywork.domain.project.errors.ProjectNotFoundException;
import kr.mywork.domain.project.repository.ProjectRepository;
import kr.mywork.domain.project.service.dto.request.ProjectCreateRequest;
import kr.mywork.domain.project.service.dto.request.ProjectUpdateRequest;
import kr.mywork.domain.project.service.dto.response.ProjectMemberResponse;
import kr.mywork.domain.project.service.dto.response.ProjectSelectResponse;
import kr.mywork.domain.project.service.dto.response.ProjectSelectWithAssignResponse;
import kr.mywork.domain.project.service.dto.response.ProjectUpdateResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

	@Value("${project.page.size}")
	private int projectPageSize;

	private final ProjectRepository projectRepository;
	private final MemberRepository memberRepository;

	@Transactional
	public UUID createProject(ProjectCreateRequest request) {
		// ProjectRepository.save(request) 안에서,
		//   1) Project 엔티티 저장
		//   2) 새로운 ProjectAssign 엔티티 생성 후 저장
		var savedProject = projectRepository.save(request);
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
	public ProjectUpdateResponse updateProject(ProjectUpdateRequest request) {
		var project = projectRepository.findById(request.getId())
			.orElseThrow(() -> new ProjectNotFoundException(ProjectErrorType.PROJECT_NOT_FOUND));

		project.updateFrom(request);
		return ProjectUpdateResponse.from(project);
	}

	@Transactional(readOnly = true)
	public ProjectSelectResponse findProjectById(UUID projectId) {
		var project = projectRepository.findById(projectId)
			.orElseThrow(() -> new ProjectNotFoundException(ProjectErrorType.PROJECT_NOT_FOUND));

		return ProjectSelectResponse.fromEntity(project);
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
