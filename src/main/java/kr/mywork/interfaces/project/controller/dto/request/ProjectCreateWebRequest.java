package kr.mywork.interfaces.project.controller.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import kr.mywork.domain.project.service.dto.request.ProjectCreateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class ProjectCreateWebRequest {
	@Length(min = 1, max = 200, message = "{project.invalid-name-length}")
	private final String name;

	private final LocalDateTime startAt;
	private final LocalDateTime endAt;

	@Length(min = 1, max = 200, message = "{project.invalid-step-length}")
	private final String step;

	@Length(min = 1, max = 500, message = "{project.invalid-detail-length}")
	private final String detail;

	private final UUID devCompanyId;
	private final UUID clientCompanyId;

	public ProjectCreateRequest toServiceDto() {
		return new ProjectCreateRequest(
			this.name,
			this.startAt,
			this.endAt,
			this.step,
			this.detail,
			this.devCompanyId,
			this.clientCompanyId
		);
	}
}
