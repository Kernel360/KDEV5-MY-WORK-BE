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

	private LocalDateTime modifiedAt;

	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime createdAt;

    public ProjectStep(final String title, final Integer orderNum, final UUID projectId, LocalDateTime modifiedAt) {
        this.title = title;
        this.orderNum = orderNum;
		this.projectId = projectId;
		this.modifiedAt = modifiedAt;
    }

	public static ProjectStep copyOf(ProjectStep projectStep) {
		ProjectStep copy = new ProjectStep(projectStep.title, projectStep.orderNum, projectStep.projectId, projectStep.modifiedAt);
		copy.id = projectStep.id;
		copy.createdAt = projectStep.createdAt;
		copy.modifiedAt = projectStep.modifiedAt;
		return copy;
	}

	public boolean update(final String title, final Integer orderNum) {
        this.title = title;
        this.orderNum = orderNum;
        this.modifiedAt = LocalDateTime.now();
        return true;
    }
}
