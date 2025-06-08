package kr.mywork.domain.member.service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record MemberSelectResponse(UUID id, String name, String email, String position, String department, String phoneNumber,
								   Boolean deleted, LocalDateTime createdAt,UUID companyId,String companyName) {
}
