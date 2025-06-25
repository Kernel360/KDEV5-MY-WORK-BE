package kr.mywork.interfaces.post.controller.dto.request;

import kr.mywork.domain.post.service.dto.response.PostApprovalRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostApprovalWebRequest {
	private String approvalStatus;

	public PostApprovalRequest toServiceDto() {
		return new PostApprovalRequest(this.approvalStatus);
	}
}
