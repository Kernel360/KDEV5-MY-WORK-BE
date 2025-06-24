package kr.mywork.interfaces.project.controller.dto.request;

import jakarta.validation.constraints.Pattern;
import kr.mywork.domain.project.service.dto.request.ProjectCreateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class ProjectCreateWebRequest {

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

	private final UUID devCompanyId;
	private final UUID clientCompanyId;

	private final Long projectAmount;

	public ProjectCreateRequest toServiceDto() {
		return new ProjectCreateRequest(
			this.name,
			this.startAt,
			this.endAt,
			this.step,
			this.detail,
			this.devCompanyId,
			this.clientCompanyId,
			this.projectAmount
		);
	}
}
