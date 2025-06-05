package kr.mywork.interfaces.member.controller.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import kr.mywork.domain.member.service.dto.request.MemberCreateRequest;

public record MemberCreateWebRequest(
	UUID companyId,
	String name,
	String department,
	String position,
	String role,
	String phoneNumber,
	String email,
	LocalDateTime birthDate
) {
	public MemberCreateRequest toServiceDto() {
		return new MemberCreateRequest(
			companyId, name, department, position, role,
			phoneNumber, email, birthDate
		);
	}
}