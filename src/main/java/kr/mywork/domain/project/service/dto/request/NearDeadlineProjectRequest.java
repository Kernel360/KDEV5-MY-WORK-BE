package kr.mywork.domain.project.service.dto.request;

import java.util.UUID;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NearDeadlineProjectRequest {
	private final UUID memberId;
	private final UUID companyId;
	private final String memberRole;
}
