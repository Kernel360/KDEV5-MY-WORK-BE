package kr.mywork.domain.project.service.dto.response;

import java.util.UUID;

public record ProjectMemberResponse(
	UUID memberId, String memberName) {

}
