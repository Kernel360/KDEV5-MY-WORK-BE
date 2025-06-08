package kr.mywork.domain.member.service.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberUpdateRequest {
	private final UUID id;
	private final UUID companyId;
	private final String name;
	private final String department;
	private final String position;
	private final String role;
	private final String phoneNumber;
	private final String email;
	private final String password;
	private final LocalDateTime birthday;
	private final boolean deleted;

	public static MemberUpdateRequest setPasswordEncode(MemberUpdateRequest memberUpdateRequest,String encodedPassword) {
		return new MemberUpdateRequest(
			memberUpdateRequest.id,
			memberUpdateRequest.companyId,
			memberUpdateRequest.name,
			memberUpdateRequest.department,
			memberUpdateRequest.position,
			memberUpdateRequest.role,
			memberUpdateRequest.phoneNumber,
			memberUpdateRequest.email,
			encodedPassword,
			memberUpdateRequest.birthday,
			memberUpdateRequest.deleted
		);
	}
}
