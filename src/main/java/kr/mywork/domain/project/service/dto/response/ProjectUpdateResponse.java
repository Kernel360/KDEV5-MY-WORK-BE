package kr.mywork.domain.project.service.dto.response;

import kr.mywork.domain.project.model.Project;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProjectUpdateResponse(
	UUID id,
	String name,
	LocalDateTime startAt,
	LocalDateTime endAt,
	String step,
	String detail,
	Boolean deleted,
	LocalDateTime createdAt,
	LocalDateTime modifiedAt,
	Long projectAmount
) {
	public static ProjectUpdateResponse from(Project project) {
		return new ProjectUpdateResponse(
			project.getId(),
			project.getName(),
			project.getStartAt(),
			project.getEndAt(),
			project.getStep(),
			project.getDetail(),
			project.getDeleted(),
			project.getCreatedAt(),
			project.getModifiedAt(),
			project.getProjectAmount()
		);
	}
}
