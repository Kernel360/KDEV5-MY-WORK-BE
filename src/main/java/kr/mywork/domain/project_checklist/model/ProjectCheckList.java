package kr.mywork.domain.project_checklist.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import kr.mywork.common.rdb.id.UnixTimeOrderedUuidGeneratedValue;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListApprovalRequest;
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

	private UUID authorId;

	private String authorName;

	private UUID companyId;

	private String companyName;

	@Column(length = 100)
	private String title;

	@Column(length = 500)
	private String content;

	@Column(nullable = false)
	private UUID projectStepId;

	@Column(nullable = false)
	private String approval; // PENDING / APPROVED / REJECTED / UPDATE_REQUEST

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime modifiedAt;

	@NotNull
	private Boolean deleted;

	public ProjectCheckList(UUID authorId, String authorName, UUID companyId, String companyName,
		String title, String content, UUID projectStepId, String approval) {
		this.authorId = authorId;
		this.authorName = authorName;
		this.companyId = companyId;
		this.companyName = companyName;
		this.title = title;
		this.content = content;
		this.projectStepId = projectStepId;
		this.approval = approval;
		this.deleted = false;
	}


	public static ProjectCheckList copyOf(ProjectCheckList projectCheckList) {
		return new ProjectCheckList(
			projectCheckList.id,
			projectCheckList.authorId,
			projectCheckList.authorName,
			projectCheckList.companyId,
			projectCheckList.companyName,
			projectCheckList.title,
			projectCheckList.content,
			projectCheckList.projectStepId,
			projectCheckList.approval,
			projectCheckList.createdAt,
			projectCheckList.modifiedAt,
			projectCheckList.deleted
		);
	}

	public void update(ProjectCheckListUpdateRequest projectCheckListUpdateRequest) {

		this.title = projectCheckListUpdateRequest.getTitle();
		this.content = projectCheckListUpdateRequest.getContent();
	}

	public void softDelete() {
		this.deleted = true;
	}

	public void changeApproval(ProjectCheckListApprovalRequest projectCheckListApprovalRequest) {
		this.approval = projectCheckListApprovalRequest.approval();
	}
}
