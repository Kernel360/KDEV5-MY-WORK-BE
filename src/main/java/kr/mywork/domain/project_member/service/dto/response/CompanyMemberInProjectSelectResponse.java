package kr.mywork.domain.project_member.service.dto.response;

import java.util.UUID;

public record CompanyMemberInProjectSelectResponse (UUID memberId,String memberName,String email) {

	public static CompanyMemberInProjectSelectResponse from(CompanyMemberInProjectResponse response) {
		return new CompanyMemberInProjectSelectResponse(
			response.memberId(),
			response.memberName(),
			response.email()
		);
	}
}
