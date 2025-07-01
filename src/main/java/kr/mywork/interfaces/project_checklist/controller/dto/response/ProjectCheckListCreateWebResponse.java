package kr.mywork.interfaces.project_checklist.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListCreateResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectCheckListCreateWebResponse {

	private String title;

	private String content;

	private UUID projectStepId;

	private String approval;

	public ProjectCheckListCreateWebResponse(ProjectCheckListCreateResponse projectCheckListCreateResponse) {
		this.title = projectCheckListCreateResponse.title();
		this.content = projectCheckListCreateResponse.content();
		this.projectStepId = projectCheckListCreateResponse.projectStepId();
		this.approval = projectCheckListCreateResponse.approval();
	}
}
