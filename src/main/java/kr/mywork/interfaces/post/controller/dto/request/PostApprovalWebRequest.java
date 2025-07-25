package kr.mywork.interfaces.post.controller.dto.request;

import jakarta.validation.constraints.Pattern;
import kr.mywork.domain.post.service.dto.response.PostApprovalRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostApprovalWebRequest {

	private static final String POST_APPROVAL_TYPE = "^(APPROVED|PENDING)?$";

	@Pattern(regexp = POST_APPROVAL_TYPE, message = "{invalid.post-approval-type}")
	private String approvalStatus;

	public PostApprovalRequest toServiceDto() {
		return new PostApprovalRequest(this.approvalStatus);
	}
}
