package kr.mywork.interfaces.project_step.dto.request;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import kr.mywork.domain.project_step.serivce.dto.request.ProjectStepCreateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class ProjectStepsCreateWebRequest {
	private final UUID projectId;

	@Valid
	private final List<ProjectStepCreateWebRequest> projectSteps;

	public List<ProjectStepCreateRequest> toServiceRequests() {
		return projectSteps.stream()
			.map(ProjectStepCreateWebRequest::toServiceRequest)
			.toList();
	}
}
