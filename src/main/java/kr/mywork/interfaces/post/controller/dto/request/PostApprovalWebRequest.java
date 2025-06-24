package kr.mywork.interfaces.post.controller.dto.request;

import kr.mywork.domain.post.service.dto.response.PostApprovalRequest;
import lombok.Getter;

@Getter
public class PostApprovalWebRequest {
	private String approvalStatus;

	public PostApprovalRequest toServiceDto() {
		return new PostApprovalRequest(this.approvalStatus);
	}
}
