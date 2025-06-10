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

	final ProjectMemberRepository projectMemberRepository;

	@Transactional
	public UUID addMemberToCompany(UUID projectId, UUID memberId) {

		ProjectMember projectMember = new ProjectMember(projectId,memberId);

		final ProjectMember savedMember = projectMemberRepository.save(projectMember);

		return savedMember.getMemberId();
	}

	@Transactional
	public List<CompanyMemberInProjectResponse> findCompanyMembersInProject (UUID projectId,UUID companyId) {

		return projectMemberRepository.findCompanyMembersInProject(projectId,companyId);
	}

	@Transactional
	public UUID deleteMemberById(UUID memberId,UUID projectId) {
		ProjectMember projectMember = projectMemberRepository.findByMemberId(memberId,projectId)
			.orElseThrow(()-> new ProjectMemberNotFoundException(ProjectMemberErrorType.PROJECT_MEMBER_NOT_FOUND));

		projectMember.delete();

		return projectMember.getId();
	}
}