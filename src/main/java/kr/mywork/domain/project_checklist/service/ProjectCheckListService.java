package kr.mywork.domain.project_checklist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.activityLog.listener.eventObject.CreateEventObject;
import kr.mywork.domain.activityLog.listener.eventObject.DeleteEventObject;
import kr.mywork.domain.activityLog.listener.eventObject.ModifyEventObject;
import kr.mywork.domain.project.errors.ProjectErrorType;
import kr.mywork.domain.project.errors.ProjectNotFoundException;
import kr.mywork.domain.project.model.Project;
import kr.mywork.domain.project.repository.ProjectRepository;
import kr.mywork.domain.project_checklist.errors.ProjectCheckListErrorType;
import kr.mywork.domain.project_checklist.errors.ProjectCheckListNotFoundException;
import kr.mywork.domain.project_checklist.listener.event.CheckListApprovalUpdateEvent;
import kr.mywork.domain.project_checklist.listener.event.CheckListHistoryCreationEvent;
import kr.mywork.domain.project_checklist.model.ProjectCheckList;
import kr.mywork.domain.project_checklist.repository.ProjectCheckListRepository;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListApprovalRequest;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListCreateRequest;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListUpdateRequest;
import kr.mywork.domain.project_checklist.service.dto.response.CheckListProjectStepProgressResponse;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListApprovalResponse;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListCreateResponse;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListDetailResponse;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListSelectResponse;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListUpdateResponse;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectStepCheckListCountResponse;
import kr.mywork.domain.project_step.model.ProjectStep;
import kr.mywork.domain.project_step.repository.ProjectStepRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectCheckListService {

	private final ProjectCheckListRepository projectCheckListRepository;
	private final ProjectStepRepository projectStepRepository;
	private final ProjectRepository projectRepository;
	private final ApplicationEventPublisher eventPublisher;
	private final ApplicationEventPublisher applicationEventPublisher;

	@Transactional
	public ProjectCheckListCreateResponse createProjectCheckList(
		ProjectCheckListCreateRequest projectCheckListRequest, LoginMemberDetail loginMemberDetail) {

		ProjectCheckList projectCheckList = projectCheckListRepository.save(projectCheckListRequest);

		eventPublisher.publishEvent(new CreateEventObject(projectCheckList, loginMemberDetail));
		eventPublisher.publishEvent(new CheckListHistoryCreationEvent(
			projectCheckList.getId(),
			loginMemberDetail.companyName(),
			loginMemberDetail.memberName(),
			"PENDING"));

		return ProjectCheckListCreateResponse.from(projectCheckList);

	}

	@Transactional(readOnly = true)
	public ProjectCheckListDetailResponse getProjectCheckList(UUID checkListId) {
		ProjectCheckList projectCheckList = projectCheckListRepository.findById(checkListId)
			.orElseThrow(
				() -> new ProjectCheckListNotFoundException(ProjectCheckListErrorType.PROJECT_CHECK_LIST_NOT_FOUND));

		return new ProjectCheckListDetailResponse(projectCheckList);
	}

	@Transactional
	public ProjectCheckListUpdateResponse updateProjectCheckList(
		ProjectCheckListUpdateRequest projectCheckListUpdateRequest, LoginMemberDetail loginMemberDetail) {
		ProjectCheckList projectCheckList = projectCheckListRepository.findById(projectCheckListUpdateRequest.getId())
			.orElseThrow(
				() -> new ProjectCheckListNotFoundException(ProjectCheckListErrorType.PROJECT_CHECK_LIST_NOT_FOUND));

		ProjectCheckList before = ProjectCheckList.copyOf(projectCheckList);

		projectCheckList.update(projectCheckListUpdateRequest);

		eventPublisher.publishEvent(new ModifyEventObject(before, projectCheckList, loginMemberDetail));

		return ProjectCheckListUpdateResponse.from(projectCheckList);
	}

	@Transactional
	public UUID deleteProjectCheckList(UUID checkListId, LoginMemberDetail loginMemberDetail) {
		ProjectCheckList projectCheckList = projectCheckListRepository.findById(checkListId)
			.orElseThrow(
				() -> new ProjectCheckListNotFoundException(ProjectCheckListErrorType.PROJECT_CHECK_LIST_NOT_FOUND));

		projectCheckList.softDelete();

		eventPublisher.publishEvent(new DeleteEventObject(projectCheckList, loginMemberDetail));

		return projectCheckList.getId();
	}

	@Transactional
	public ProjectCheckListApprovalResponse approvalProjectCheckList(
		ProjectCheckListApprovalRequest projectCheckListApprovalRequest, LoginMemberDetail loginMemberDetail) {
		ProjectCheckList projectCheckList = projectCheckListRepository.findById(projectCheckListApprovalRequest.id())
			.orElseThrow(
				() -> new ProjectCheckListNotFoundException(ProjectCheckListErrorType.PROJECT_CHECK_LIST_NOT_FOUND));

		ProjectCheckList before = ProjectCheckList.copyOf(projectCheckList);

		projectCheckList.changeApproval(projectCheckListApprovalRequest);

		eventPublisher.publishEvent(new ModifyEventObject(before, projectCheckList, loginMemberDetail));
		applicationEventPublisher.publishEvent(
			new CheckListApprovalUpdateEvent(
				projectCheckListApprovalRequest.id(),
				projectCheckListApprovalRequest.approval(),
				projectCheckListApprovalRequest.reason(),
				loginMemberDetail.companyName(),
				loginMemberDetail.memberName()));


		return ProjectCheckListApprovalResponse.from(projectCheckList);
	}

	@Transactional
	public List<CheckListProjectStepProgressResponse> getCheckListProgress(final UUID projectId,
		final String approval) {

		final List<ProjectStep> projectSteps = projectStepRepository.findAllByProjectId(projectId);
		final Map<UUID, ProjectStep> projectStepMap = projectSteps.stream()
			.collect(Collectors.toMap(ProjectStep::getId, projectStep -> projectStep));

		final List<ProjectStepCheckListCountResponse> projectStepTotalCountResponses =
			projectCheckListRepository.findProgressCountGroupByProjectStepIdAndApproval(projectStepMap.keySet(), null);
		final List<ProjectStepCheckListCountResponse> projectStepApprovalCountResponses =
			projectCheckListRepository.findProgressCountGroupByProjectStepIdAndApproval(projectStepMap.keySet(),
				approval);

		return transformProjectStepProgress(projectStepTotalCountResponses, projectStepApprovalCountResponses,
			projectStepMap);
	}

	private List<CheckListProjectStepProgressResponse> transformProjectStepProgress(
		final List<ProjectStepCheckListCountResponse> projectStepTotalCountResponses,
		final List<ProjectStepCheckListCountResponse> projectStepApprovalCountResponses,
		final Map<UUID, ProjectStep> projectStepMap) {

		// findProgressCountGroupByProjectStepIdAndApproval 에서 순서를 보장해주므로 해당 코드 정상 동작
		List<CheckListProjectStepProgressResponse> checkListProjectStepProgressResponses = new ArrayList<>();
		for (int i = 0; i < projectStepTotalCountResponses.size(); i++) {
			final ProjectStepCheckListCountResponse projectStepTotalCountResponse =
				projectStepTotalCountResponses.get(i);
			final ProjectStepCheckListCountResponse projectStepApprovalCountResponse =
				projectStepApprovalCountResponses.get(i);

			final UUID projectStepId = projectStepTotalCountResponse.projectStepId();
			final String projectStepName = projectStepMap.get(projectStepId).getTitle();

			checkListProjectStepProgressResponses.add(new CheckListProjectStepProgressResponse(
				projectStepId,
				projectStepName,
				projectStepTotalCountResponse.count(),
				projectStepApprovalCountResponse.count()));
		}

		return checkListProjectStepProgressResponses;
	}

	@Transactional
	public List<ProjectCheckListSelectResponse> findAllByProjectIdAndProjectStepId(final UUID projectId,
		final UUID projectStepId) {
		final Project project = projectRepository.findById(projectId)
			.orElseThrow(() -> new ProjectNotFoundException(ProjectErrorType.PROJECT_NOT_FOUND));

		return projectCheckListRepository.findAllByProjectIdAndStepId(project.getId(), projectStepId);
	}
}
