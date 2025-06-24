package kr.mywork.interfaces.post.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.post.service.dto.response.PostApprovalResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostApprovalWebResponse {
	private final UUID id;
	private final String approvalStatus;

	public PostApprovalWebResponse(PostApprovalResponse postApprovalResponse) {
		this.id = postApprovalResponse.getId();
		this.approvalStatus = postApprovalResponse.getApprovalStatus();
	}
}
