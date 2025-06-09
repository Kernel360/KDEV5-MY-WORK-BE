package kr.mywork.interfaces.member.controller.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
	Boolean deleted,
	LocalDateTime modifiedAt,
	LocalDateTime createdAt,
	String contactPhoneNumber,
	List<MemberProjectInfoWebResponse> projects
) {

	public static MemberDetailsWebResponse from(
		MemberDetailResponse memberDetailResponse, List<MemberProjectInfoWebResponse> projects) {
		return new MemberDetailsWebResponse(
			memberDetailResponse.companyId(),
			memberDetailResponse.companyName(),
			memberDetailResponse.name(),
			memberDetailResponse.department(),
			memberDetailResponse.position(),
			memberDetailResponse.role(),
			memberDetailResponse.phoneNumber(),
			memberDetailResponse.email(),
			memberDetailResponse.deleted(),
			memberDetailResponse.modifiedAt(),
			memberDetailResponse.createdAt(),
			memberDetailResponse.contactPhoneNumber(),
			projects
		);
	}
}
