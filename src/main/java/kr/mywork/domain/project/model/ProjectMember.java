package kr.mywork.domain.project.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import kr.mywork.common.rdb.id.UnixTimeOrderedUuidGeneratedValue;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProjectMember {

	@Id
	@UnixTimeOrderedUuidGeneratedValue
	private UUID id;

	@Column(name = "project_id", nullable = false, columnDefinition = "BINARY(16)")
	private UUID projectId;

	@Getter
	@Column(name = "member_id", nullable = false, columnDefinition = "BINARY(16)")
	private UUID memberId;

	@Column(name = "manager", nullable = false, columnDefinition = "bit")
	private Boolean manager;

	@Column(name = "deleted", nullable = false, columnDefinition = "bit")
	private Boolean deleted;

	@Column(name = "created_at", nullable = false, columnDefinition = "timestamp")
	@CreationTimestamp
	private LocalDateTime createdAt;


	public ProjectMember(UUID projectId, UUID memberId) {
		this.projectId = projectId;
		this.memberId = memberId;
		this.manager = false;
		this.deleted = false;
		this.createdAt = LocalDateTime.now();
	}
}
