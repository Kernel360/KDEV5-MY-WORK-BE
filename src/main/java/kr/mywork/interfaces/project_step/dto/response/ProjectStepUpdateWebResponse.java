package kr.mywork.interfaces.project_step.dto.response;

import java.util.UUID;

import kr.mywork.domain.project_step.serivce.dto.response.ProjectStepUpdateResponse;

public record ProjectStepUpdateWebResponse(UUID projectStepId, String title, Integer orderNumber) {

	public static ProjectStepUpdateWebResponse fromServiceResponse(final ProjectStepUpdateResponse serviceResponse) {
		return new ProjectStepUpdateWebResponse(
			serviceResponse.projectStepId(),
			serviceResponse.title(),
			serviceResponse.orderNumber());
	}
}
