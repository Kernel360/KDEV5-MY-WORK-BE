package kr.mywork.domain.project_member.service;

import jakarta.transaction.Transactional;
import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.activityLog.listener.eventObject.CreateEventObject;
import kr.mywork.domain.activityLog.listener.eventObject.DeleteEventObject;
import kr.mywork.domain.project.model.ProjectMember;
import kr.mywork.domain.project_member.error.ProjectMemberErrorType;
import kr.mywork.domain.project_member.error.ProjectMemberNotFoundException;
import kr.mywork.domain.project_member.repository.ProjectMemberRepository;
import kr.mywork.domain.project_member.service.dto.request.ProjectManagerUpdateRequest;
import kr.mywork.domain.project_member.service.dto.response.CompanyMemberInProjectResponse;
import kr.mywork.domain.project_member.service.dto.response.ProjectMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {

	private final ProjectMemberRepository projectMemberRepository;

	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public UUID addMemberToCompany(UUID projectId, UUID memberId, LoginMemberDetail loginMemberDetail) {
		final boolean hasDeletedProjectMember =
			projectMemberRepository.existsByMemberIdAndProjectIdAndDeleted(memberId, projectId, true);

		if (hasDeletedProjectMember) {
			return restoreProjectMember(projectId, memberId, loginMemberDetail);
		}

		return createProjectMember(projectId, memberId, loginMemberDetail);
	}

	private UUID createProjectMember(final UUID projectId, final UUID memberId, LoginMemberDetail loginMemberDetail) {
		final ProjectMember savedMember = projectMemberRepository.save(new ProjectMember(projectId, memberId));

		eventPublisher.publishEvent(new CreateEventObject(savedMember, loginMemberDetail));

		return savedMember.getMemberId();
	}

	private UUID restoreProjectMember(final UUID projectId, final UUID memberId, LoginMemberDetail loginMemberDetail) {
		final ProjectMember projectMember = projectMemberRepository.findByMemberIdAndProjectId(memberId, projectId)
			.orElseThrow(() -> new ProjectMemberNotFoundException(ProjectMemberErrorType.PROJECT_MEMBER_NOT_FOUND));

		projectMember.restore();

		eventPublisher.publishEvent(new CreateEventObject(projectMember, loginMemberDetail));

		return projectMember.getId();
	}

	@Transactional
	public List<CompanyMemberInProjectResponse> findCompanyMembersInProject(UUID projectId, UUID companyId) {

		return projectMemberRepository.findCompanyMembersInProject(projectId, companyId);
	}

	@Transactional
	public UUID deleteMemberById(UUID memberId, UUID projectId, LoginMemberDetail loginMemberDetail) {
		ProjectMember projectMember = projectMemberRepository.findByMemberIdAndProjectId(memberId, projectId)
			.orElseThrow(() -> new ProjectMemberNotFoundException(ProjectMemberErrorType.PROJECT_MEMBER_NOT_FOUND));

		projectMember.delete();

		eventPublisher.publishEvent(new DeleteEventObject(projectMember, loginMemberDetail));

		return projectMember.getId();
	}
	@Transactional
	public Optional<ProjectMemberDto> findProjectManagerByMemberIdAndProjectId(UUID memberId, UUID projectId){

        return projectMemberRepository.findProjectManagerByMemberIdAndProjectId(memberId,projectId);
	}

	@Transactional
	public UUID updateProjectManager(ProjectManagerUpdateRequest projectManagerUpdateRequest){
		UUID memberId = projectManagerUpdateRequest.memberId();
		UUID projectId = projectManagerUpdateRequest.projectId();

		ProjectMember projectMember = projectMemberRepository.findByMemberIdAndProjectId(memberId, projectId)
				.orElseThrow(() -> new ProjectMemberNotFoundException(ProjectMemberErrorType.PROJECT_MEMBER_NOT_FOUND));

		//true -> false , false -> true
		projectMember.changeManager(projectMember.getManager());


		return projectMember.getMemberId();
	}
}
