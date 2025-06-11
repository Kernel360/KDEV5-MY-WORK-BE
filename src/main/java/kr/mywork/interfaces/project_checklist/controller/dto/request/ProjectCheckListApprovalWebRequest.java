package kr.mywork.interfaces.project_checklist.controller.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListApprovalRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectCheckListApprovalWebRequest {

	private static final String APPROVAL_TYPE_REGX = "^(APPROVED|REJECTED|REQUEST_CHANGES|PENDING)$";

	@NotNull
	private UUID id;

	@NotBlank
	@Pattern(regexp = APPROVAL_TYPE_REGX, message = "{invalid.approval-type}")
	private String approval;

	public ProjectCheckListApprovalRequest toServiceDto() {
		return new ProjectCheckListApprovalRequest(this.id, this.approval);
	}
}
