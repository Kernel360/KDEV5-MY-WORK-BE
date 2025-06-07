package kr.mywork.domain.project_checklist.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import kr.mywork.common.rdb.id.UnixTimeOrderedUuidGeneratedValue;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCheckList {

	@Id
	@UnixTimeOrderedUuidGeneratedValue
	private UUID id;

	@Column(length = 100)
	private String title;

	@Column(nullable = false)
	private UUID devCompanyId;

	@Column(nullable = false)
	private UUID clientCompanyId;

	@Column(nullable = false)
	private UUID projectStepId;

	@Column(nullable = false)
	private String approval;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@NotNull
	private Boolean deleted;

	public ProjectCheckList(String title, UUID devCompanyId, UUID clientCompanyId,
		UUID projectStepId, String approval) {
		this.title = title;
		this.devCompanyId = devCompanyId;
		this.clientCompanyId = clientCompanyId;
		this.projectStepId = projectStepId;
		this.approval = approval;
		this.deleted = false;
	}

	public void update(ProjectCheckListUpdateRequest projectCheckListUpdateRequest) {

		this.title = projectCheckListUpdateRequest.getTitle();
	}

	public void softDelete() {
		this.deleted = true;
	}
}
