package kr.mywork.domain.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.mywork.common.rdb.id.UnixTimeOrderedUuidGeneratedValue;
import kr.mywork.domain.project.service.dto.request.ProjectUpdateRequest;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Project {

	@Id
	@UnixTimeOrderedUuidGeneratedValue
	private UUID id;

	@Column(length = 200, nullable = false)
	private String name;

	@Column(nullable = false, columnDefinition = "timestamp")
	private LocalDateTime startAt;

	@Column(nullable = false, columnDefinition = "timestamp")
	private LocalDateTime endAt;

	@Column(length = 200)
	private String step;

	@Column(nullable = false, columnDefinition = "timestamp")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(nullable = false, columnDefinition = "timestamp")
	@UpdateTimestamp
	private LocalDateTime modifiedAt;

	@Column(name = "detail", length = 500)
	private String detail;

	@Column(name = "deleted", nullable = false)
	@Setter
	private Boolean deleted = false;

	@Column(nullable = false, name = "project_amount")
	private long projectAmount = 0L;

	public Project(
		final String name,
		final LocalDateTime startAt,
		final LocalDateTime endAt,
		final String step,
		final String detail,
		final Long projectAmount
	) {
		this.name = name;
		this.startAt = startAt;
		this.endAt = endAt;
		this.step = step;
		this.detail = detail;
		this.projectAmount = projectAmount;
	}

	public static Project copyOf(Project project) {
		return new Project(project.id,
							project.name,
							project.startAt,
							project.endAt,
							project.step,
							project.createdAt,
							project.modifiedAt,
							project.detail,
							project.deleted,
							project.projectAmount);
	}

	public void updateFrom(ProjectUpdateRequest request) {
		this.name = request.name();
		this.startAt = request.startAt();
		this.endAt = request.endAt();
		this.step = request.step();
		this.detail = request.detail();
		this.deleted = request.deleted();
		this.projectAmount = request.projectAmount();
	}
	public void updateStatus(String status){
		this.step = status;
	}
}
