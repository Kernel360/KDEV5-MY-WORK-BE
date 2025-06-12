package kr.mywork.interfaces.project_checklist.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.project_checklist.service.dto.response.CheckListProjectStepProgressResponse;

public record ProjectCheckListProgressWebResponse(
	UUID projectStepId, String projectStepName, Long totalCount, Long approvalCount) {

	public static ProjectCheckListProgressWebResponse fromServiceResponse(
		CheckListProjectStepProgressResponse checkListProjectStepProgressResponse) {
		return new ProjectCheckListProgressWebResponse(
			checkListProjectStepProgressResponse.projectStepId(),
			checkListProjectStepProgressResponse.projectStepName(),
			checkListProjectStepProgressResponse.totalCount(),
			checkListProjectStepProgressResponse.approvalCount());
	}
}
