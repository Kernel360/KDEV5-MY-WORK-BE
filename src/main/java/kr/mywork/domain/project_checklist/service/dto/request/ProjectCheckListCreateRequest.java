package kr.mywork.domain.project_checklist.service.dto.request;

import java.util.UUID;

import kr.mywork.domain.project_checklist.model.ProjectCheckList;
import lombok.Getter;

@Getter
public class ProjectCheckListCreateRequest {

	private String title;

	private UUID devCompanyId;

	private UUID clientCompanyId;

	private UUID projectStepId;

	private Boolean approval;

	public ProjectCheckListCreateRequest(String title, UUID devCompanyId, UUID clientCompanyId, UUID projectStepId,
		Boolean approval) {
		this.title = title;
		this.devCompanyId = devCompanyId;
		this.clientCompanyId = clientCompanyId;
		this.projectStepId = projectStepId;
		this.approval = approval;
	}

	public ProjectCheckList toEntity() {
		return new ProjectCheckList(
			this.title,
			this.devCompanyId,
			this.clientCompanyId,
			this.projectStepId,
			this.approval
		);
	}

}
