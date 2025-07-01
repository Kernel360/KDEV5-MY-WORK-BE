package kr.mywork.interfaces.project_step.dto.request;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import kr.mywork.domain.project_step.serivce.dto.request.ProjectStepUpdateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class ProjectStepUpdateWebRequest {
	private final UUID projectStepId;

	@Length(min = 1, max = 30, message = "{project-step.invalid-title}")
	private final String title;

	@Positive(message = "{project-step.positive}")
	@Max(value = Integer.MAX_VALUE, message = "{project-step.max-value}")
	private final Integer orderNumber;

	public ProjectStepUpdateRequest toServiceRequest() {
		return new ProjectStepUpdateRequest(projectStepId, title, orderNumber);
	}
}
