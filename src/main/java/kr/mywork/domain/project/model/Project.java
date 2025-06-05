package kr.mywork.domain.project.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.mywork.common.rdb.id.UnixTimeOrderedUuidGeneratedValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

	@Id
	@UnixTimeOrderedUuidGeneratedValue
	private UUID id;

	@Column(name = "name", length = 200, nullable = false)
	private String name;

	/** 프로젝트 시작일 */
	@Column(nullable = false, columnDefinition = "timestamp")
	private LocalDateTime startAt;

	/** 프로젝트 종료일 */
	@Column(nullable = false, columnDefinition = "timestamp")
	private LocalDateTime endAt;

	/** 프로젝트 단계 */
	@Column(name = "step", length = 200)
	private String step;

	/** 프로젝트 생성 일자 */
	@Column(nullable = false, columnDefinition = "timestamp")
	@CreationTimestamp
	private LocalDateTime createdAt;

	/** 프로젝트 수정 일자 */
	@Column(nullable = false, columnDefinition = "timestamp")
	@UpdateTimestamp
	private LocalDateTime modifiedAt;

	/** 프로젝트 상세 */
	@Column(name = "detail", length = 500)
	private String detail;

	/** 프로젝트 삭제 여부 (0 또는 1) */
	@Column(name = "deleted", nullable = false)
	private Boolean deleted;
}
