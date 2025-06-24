package kr.mywork.domain.post.service.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PostApprovalRequest {
	private final String approvalStatus;
}
