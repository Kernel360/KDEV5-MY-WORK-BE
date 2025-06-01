package kr.mywork.interfaces.project_step.dto.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import kr.mywork.domain.project_step.serivce.dto.request.ProjectStepCreateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class ProjectStepCreateWebRequest {
	@Length(min = 1, max = 30, message = "{project-step.invalid-title}")
	private final String title;

	@Positive(message = "{project-step.positive}")
	@Max(value = Integer.MAX_VALUE, message = "{project-step.max-value}")
	private final Integer orderNumber;

	public ProjectStepCreateRequest toServiceRequest() {
		return new ProjectStepCreateRequest(title, orderNumber);
	}
}
