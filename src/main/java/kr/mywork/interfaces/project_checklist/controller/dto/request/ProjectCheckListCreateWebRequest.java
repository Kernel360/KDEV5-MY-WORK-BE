package kr.mywork.interfaces.project_checklist.controller.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectCheckListCreateWebRequest {

	@NotBlank
	@Size(min = 1, max = 100)
	private String title;

	@NotNull
	private UUID devCompanyId;

	@NotNull
	private UUID clientCompanyId;

	@NotNull
	private UUID projectStepId;

	@NotNull
	private String approval;

	public ProjectCheckListCreateRequest toServiceDto() {
		return new ProjectCheckListCreateRequest(this.title, this.devCompanyId, this.clientCompanyId,
			this.projectStepId, this.approval);
	}
}
