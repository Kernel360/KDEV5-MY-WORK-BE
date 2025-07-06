package kr.mywork.domain.project_step.serivce;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.activityLog.listener.eventObject.ActivityLogCreateEvent;
import kr.mywork.domain.activityLog.listener.eventObject.ActivityModifyEvent;
import kr.mywork.domain.project_step.model.ProjectStep;
import kr.mywork.domain.project_step.repository.ProjectStepRepository;
import kr.mywork.domain.project_step.serivce.dto.request.ProjectStepCreateRequest;
import kr.mywork.domain.project_step.serivce.dto.request.ProjectStepUpdateRequest;
import kr.mywork.domain.project_step.serivce.dto.response.ProjectStepGetResponse;
import kr.mywork.domain.project_step.serivce.dto.response.ProjectStepUpdateResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectStepService {

	private final ProjectStepRepository projectStepRepository;
	private final ApplicationEventPublisher eventPublisher;

	public Integer saveAll(
		final UUID projectId, final List<ProjectStepCreateRequest> projectStepCreateRequests, LoginMemberDetail loginMemberDetail) {
		// TODO projectId 를 기반으로 Project 존재 유무 검증 필요

		final List<ProjectStep> projectSteps = projectStepCreateRequests.stream()
			.map(request -> request.toEntity(projectId))
			.toList();

		final List<ProjectStep> savedProjectSteps = projectStepRepository.saveAll(projectSteps);

		projectSteps.forEach(projectStep -> {
			eventPublisher.publishEvent(new ActivityLogCreateEvent(projectStep, loginMemberDetail));
		});

		return savedProjectSteps.size();
	}

	public List<ProjectStepUpdateResponse> updateProjectSteps(
		final UUID projectId, final List<ProjectStepUpdateRequest> projectStepUpdateRequests,
		LoginMemberDetail loginMemberDetail) {
		// TODO ProjectId 검증 로직 추가

		final Map<UUID, ProjectStepUpdateRequest> projectStepUpdateRequestMap =
			transformProjectStepUpdateMap(projectStepUpdateRequests);

		final Set<UUID> projectStepIds = projectStepUpdateRequestMap.keySet();
		List<ProjectStep> projectSteps = projectStepRepository.findAllByIds(projectStepIds);

		projectSteps.forEach(projectStep -> {
			final ProjectStepUpdateRequest projectStepUpdateRequest =
				projectStepUpdateRequestMap.get(projectStep.getId());

			ProjectStep before = ProjectStep.copyOf(projectStep);
			projectStep.update(projectStepUpdateRequest.title(), projectStepUpdateRequest.orderNum());
			eventPublisher.publishEvent(new ActivityModifyEvent(before, projectStep, loginMemberDetail));
		});

		return projectSteps.stream().map(ProjectStepUpdateResponse::fromEntity).toList();
	}

	private Map<UUID, ProjectStepUpdateRequest> transformProjectStepUpdateMap(
		final List<ProjectStepUpdateRequest> projectStepUpdateRequests) {
		return projectStepUpdateRequests.stream().collect(
			Collectors.toMap(ProjectStepUpdateRequest::projectStepId,
				projectStepUpdateRequest -> projectStepUpdateRequest));
	}

	public List<ProjectStepGetResponse> getProjectSteps(UUID projectId) {
		// TODO ProjectId 검증 로직 추가
		List<ProjectStep> steps = projectStepRepository.findAllStepsByProjectIdOrderByNumAsc(projectId);

		return steps.stream()
			.map(ProjectStepGetResponse::fromEntity)
			.toList();


	}

}
