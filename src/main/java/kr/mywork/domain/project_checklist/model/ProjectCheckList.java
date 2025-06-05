package kr.mywork.domain.project_checklist.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.mywork.common.rdb.id.UnixTimeOrderedUuidGeneratedValue;
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
	private Boolean approval;

	public ProjectCheckList(String title, UUID devCompanyId, UUID clientCompanyId,
		UUID projectStepId, Boolean approval) {
		this.title = title;
		this.devCompanyId = devCompanyId;
		this.clientCompanyId = clientCompanyId;
		this.projectStepId = projectStepId;
		this.approval = approval;
	}
}
