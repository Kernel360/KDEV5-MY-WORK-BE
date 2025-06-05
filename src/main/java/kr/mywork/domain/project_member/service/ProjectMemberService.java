package kr.mywork.domain.project_member.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.mywork.domain.project.model.ProjectMember;
import kr.mywork.domain.project_member.repository.ProjectMemberRepository;
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
}