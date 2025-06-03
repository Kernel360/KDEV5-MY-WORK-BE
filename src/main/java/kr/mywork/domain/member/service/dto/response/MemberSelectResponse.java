package kr.mywork.domain.member.service.dto.response;

import java.time.LocalDateTime;

public record MemberSelectResponse(String name, String email, String position, String department, String phoneNumber,
								   Boolean deleted, LocalDateTime createdAt) {
}
