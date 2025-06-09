package kr.mywork.domain.company.service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record MemberDetailResponse(
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
	String contactPhoneNumber
) {
}
