package kr.mywork.interfaces.project_checklist.controller.dto.request;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListApprovalRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ProjectCheckListApprovalWebRequest {

	private static final String APPROVAL_TYPE_REGX = "^(APPROVED|REJECTED|REQUEST_CHANGES|PENDING)$";

	@NotNull
	private final UUID id;

	@NotBlank
	@Pattern(regexp = APPROVAL_TYPE_REGX, message = "{invalid.approval-type}")
	private final String approval;

	@Length(min = 1, max = 200, message = "{check-list.invalid-reason}")
	@NotBlank
	private final String reason;

	public ProjectCheckListApprovalRequest toServiceDto() {
		return new ProjectCheckListApprovalRequest(this.id, this.approval, reason);
	}
}
