package kr.mywork.domain.project_member.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.mywork.domain.project.model.ProjectMember;
import kr.mywork.domain.project_member.error.ProjectMemberErrorType;
import kr.mywork.domain.project_member.error.ProjectMemberNotFoundException;
import kr.mywork.domain.project_member.repository.ProjectMemberRepository;
import kr.mywork.domain.project_member.service.dto.response.CompanyMemberInProjectResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {

	private final ProjectMemberRepository projectMemberRepository;

	@Transactional
	public UUID addMemberToCompany(UUID projectId, UUID memberId) {
		final boolean hasDeletedProjectMember =
			projectMemberRepository.existsByMemberIdAndProjectIdAndDeleted(memberId, projectId, true);

		if (hasDeletedProjectMember) {
			return restoreProjectMember(projectId, memberId);
		}

		return createProjectMember(projectId, memberId);
	}

	private UUID createProjectMember(final UUID projectId, final UUID memberId) {
		final ProjectMember savedMember = projectMemberRepository.save(new ProjectMember(projectId, memberId));

		return savedMember.getMemberId();
	}

	private UUID restoreProjectMember(final UUID projectId, final UUID memberId) {
		final ProjectMember projectMember = projectMemberRepository.findByMemberIdAndProjectId(projectId, memberId)
			.orElseThrow(() -> new ProjectMemberNotFoundException(ProjectMemberErrorType.PROJECT_MEMBER_NOT_FOUND));

		projectMember.restore();

		return projectMember.getId();
	}

	@Transactional
	public List<CompanyMemberInProjectResponse> findCompanyMembersInProject(UUID projectId, UUID companyId) {

		return projectMemberRepository.findCompanyMembersInProject(projectId, companyId);
	}

	@Transactional
	public UUID deleteMemberById(UUID memberId, UUID projectId) {
		ProjectMember projectMember = projectMemberRepository.findByMemberIdAndProjectId(memberId, projectId)
			.orElseThrow(() -> new ProjectMemberNotFoundException(ProjectMemberErrorType.PROJECT_MEMBER_NOT_FOUND));

		projectMember.delete();

		return projectMember.getId();
	}
}
