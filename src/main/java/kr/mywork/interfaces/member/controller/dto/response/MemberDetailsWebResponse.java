package kr.mywork.interfaces.member.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.auth.dto.MemberDetails;
import kr.mywork.domain.company.service.dto.response.MemberDetailResponse;

public record MemberDetailsWebResponse(
	UUID companyId,
	String companyName,
	String name,
	String department,
	String position,
	String role,
	String phoneNumber,
	String email,
	Boolean deleted
) {
	public static MemberDetailsWebResponse from(MemberDetailResponse response) {
		return new MemberDetailsWebResponse(
			response.companyId(),
			response.companyName(),
			response.name(),
			response.department(),
			response.position(),
			response.role(),
			response.phoneNumber(),
			response.email(),
			response.deleted()
		);
	}
}
