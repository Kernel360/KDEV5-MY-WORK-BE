package kr.mywork.interfaces.project_step.dto.request;

import java.util.List;

import jakarta.validation.Valid;
import kr.mywork.domain.project_step.serivce.dto.request.ProjectStepUpdateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class ProjectStepsUpdateWebRequest {
	@Valid
	private final List<ProjectStepUpdateWebRequest> projectStepUpdateWebRequests;

	public List<ProjectStepUpdateRequest> toServiceRequests() {
		return projectStepUpdateWebRequests.stream()
			.map(ProjectStepUpdateWebRequest::toServiceRequest)
			.toList();
	}
}
