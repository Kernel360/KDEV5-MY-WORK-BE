package kr.mywork.interfaces.project.controller.dto.request;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Pattern;
import kr.mywork.domain.project.service.dto.request.ProjectUpdateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class ProjectUpdateWebRequest {

	private static final String PROJECT_KEYWORD_TYPE = "^(NOT_STARTED|IN_PROGRESS|PAUSED|COMPLETED)$";

	@Length(min = 1, max = 200, message = "{project.invalid-name-length}")
	private final String name;

	private final LocalDateTime startAt;
	private final LocalDateTime endAt;

	@Length(min = 1, max = 200, message = "{project.invalid-step-length}")
	@Pattern(regexp = PROJECT_KEYWORD_TYPE, message = "{project.invalid-status}")
	private final String step;

	@Length(min = 1, max = 500, message = "{project.invalid-detail-length}")
	private final String detail;
	private final Boolean deleted;

	public ProjectUpdateRequest toServiceDto() {
		return new ProjectUpdateRequest(
			this.name,
			this.startAt,
			this.endAt,
			this.step,
			this.detail,
			this.deleted
		);
	}
}
