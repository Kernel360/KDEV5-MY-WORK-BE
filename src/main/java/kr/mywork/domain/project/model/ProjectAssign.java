package kr.mywork.domain.project.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import kr.mywork.common.rdb.id.UnixTimeOrderedUuidGeneratedValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectAssign {

	@Id
	@UnixTimeOrderedUuidGeneratedValue
	private UUID id;

	@Column(name = "project_id", nullable = false, columnDefinition = "BINARY(16)")
	private UUID projectId;

	@Column(name = "dev_company_id", nullable = false, columnDefinition = "BINARY(16)")
	private UUID devCompanyId;

	@Column(name = "client_company_id", nullable = false, columnDefinition = "BINARY(16)")
	private UUID clientCompanyId;

	@Column(name = "created_at", nullable = false, columnDefinition = "timestamp")
	@CreationTimestamp
	private LocalDateTime createdAt;

}
