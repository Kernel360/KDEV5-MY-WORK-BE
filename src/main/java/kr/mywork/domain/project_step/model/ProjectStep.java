package kr.mywork.domain.project_step.model;

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
public class ProjectStep {

	@Id
	@UnixTimeOrderedUuidGeneratedValue
	private UUID id;

	private UUID projectId;

	@Column(length = 30)
	private String title;

	private Integer orderNum;

	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime createdAt;

    public ProjectStep(final String title, final Integer orderNum, final UUID projectId) {
        this.title = title;
        this.orderNum = orderNum;
		this.projectId = projectId;
    }

	public boolean update(final String title, final Integer orderNum) {
        this.title = title;
        this.orderNum = orderNum;
        return true;
    }
}
