package kr.mywork.interfaces.project_step.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import kr.mywork.domain.project_step.serivce.dto.response.ProjectStepUpdateResponse;

public record ProjectStepsUpdateWebResponse (@JsonProperty("projectSteps") List<ProjectStepUpdateWebResponse> projectStepUpdateWebResponses) {

	public static List<ProjectStepUpdateWebResponse> fromServiceResponses(
		final List<ProjectStepUpdateResponse> projectStepUpdateWebResponses) {
		return projectStepUpdateWebResponses.stream()
			.map(ProjectStepUpdateWebResponse::fromServiceResponse)
			.toList();
	}
}
