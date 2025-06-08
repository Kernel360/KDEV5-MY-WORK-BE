package kr.mywork.interfaces.project_checklist.controller.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListApprovalRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectCheckListApprovalWebRequest {

	@NotNull
	private UUID id;

	@NotBlank
	private String approval;

	public ProjectCheckListApprovalRequest toServiceDto() {
		return new ProjectCheckListApprovalRequest(this.id, this.approval);
	}
}
