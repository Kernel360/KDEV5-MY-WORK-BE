package kr.mywork.domain.project.service.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import kr.mywork.domain.project.model.Project;

public record ProjectCreateRequest(String name, LocalDateTime startAt, LocalDateTime endAt, String step, String detail,
								   UUID devCompanyId, UUID clientCompanyId) {
	public Project toEntity() {
		return new Project(
			this.name,
			this.startAt,
			this.endAt,
			this.step,
			this.detail
		);
	}
}

