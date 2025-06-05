package kr.mywork.interfaces.project_checklist.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListCreateResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectCheckListCreateWebResponse {

	private String title;

	private UUID devCompanyId;

	private UUID clientCompanyId;

	private UUID projectStepId;

	private Boolean approval;

	public ProjectCheckListCreateWebResponse(ProjectCheckListCreateResponse projectCheckListCreateResponse) {
		this.title = projectCheckListCreateResponse.title();
		this.devCompanyId = projectCheckListCreateResponse.devCompanyId();
		this.clientCompanyId = projectCheckListCreateResponse.clientCompanyId();
		this.projectStepId = projectCheckListCreateResponse.projectStepId();
		this.approval = projectCheckListCreateResponse.approval();
	}
}
