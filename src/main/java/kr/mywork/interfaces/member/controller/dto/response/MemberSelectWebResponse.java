package kr.mywork.interfaces.member.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.member.service.dto.response.MemberSelectResponse;

public record MemberSelectWebResponse(UUID id, String name, String email, String position, String department,
									  String phoneNumber,
									  Boolean deleted,
									  @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime createdAt,
									  UUID companyId, String companyName) {

	public static MemberSelectWebResponse from(MemberSelectResponse memberSelectResponse) {
		return new MemberSelectWebResponse(
			memberSelectResponse.id(),
			memberSelectResponse.name(),
			memberSelectResponse.email(),
			memberSelectResponse.position(),
			memberSelectResponse.department(),
			memberSelectResponse.phoneNumber(),
			memberSelectResponse.deleted(),
			memberSelectResponse.createdAt(),
			memberSelectResponse.companyId(),
			memberSelectResponse.companyName()
		);
	}
}
