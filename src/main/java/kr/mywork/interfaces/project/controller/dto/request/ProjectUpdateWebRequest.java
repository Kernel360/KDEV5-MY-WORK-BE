package kr.mywork.interfaces.project.controller.dto.request;

import jakarta.validation.constraints.Pattern;
import kr.mywork.domain.project.service.dto.request.ProjectUpdateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class ProjectUpdateWebRequest {

	private static final String PROJECT_KEYWORD_TYPE = "^(CONTRACT|IN_PROGRESS|PAYMENT|COMPLETED)$";

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

	private final Long projectAmount;

	public ProjectUpdateRequest toServiceDto() {
		return new ProjectUpdateRequest(
			this.name,
			this.startAt,
			this.endAt,
			this.step,
			this.detail,
			this.deleted,
			this.projectAmount
		);
	}
}
