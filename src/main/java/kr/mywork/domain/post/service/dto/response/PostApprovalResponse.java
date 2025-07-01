package kr.mywork.domain.post.service.dto.response;

import java.util.UUID;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PostApprovalResponse {
	private final UUID id;
	private final String approvalStatus;

}
