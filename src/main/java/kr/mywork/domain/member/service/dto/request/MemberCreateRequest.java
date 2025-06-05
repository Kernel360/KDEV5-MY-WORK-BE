package kr.mywork.domain.member.service.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberCreateRequest {
	private final UUID companyId;
	private final String name;
	private final String department;
	private final String position;
	private final String role;
	private final String phoneNumber;
	private final String email;
	private final LocalDateTime birthDate;
}
