package kr.mywork.domain.member.service.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

public record MemberUpdateRequest(UUID id, UUID companyId, String name, String department, String position,
	 String role, String phoneNumber, String email, LocalDateTime birthDate, boolean deleted) {
}
