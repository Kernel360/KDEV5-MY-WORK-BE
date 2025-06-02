package kr.mywork.domain.project_step.model;

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
public class ProjectStep {

	@Id
	@UnixTimeOrderedUuidGeneratedValue
	private UUID id;

	@Column(length = 30)
	private String title;

	private Integer orderNum;

	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime createdAt;

	public ProjectStep(final String title, final Integer orderNum) {
		this.title = title;
		this.orderNum = orderNum;
	}
}
