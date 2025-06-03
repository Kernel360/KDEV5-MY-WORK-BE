package kr.mywork.interfaces.member.controller.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import kr.mywork.domain.member.service.dto.request.MemberUpdateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class MemberUpdateWebRequest {
	private final UUID id;
	private final UUID companyId;
	private final String name;
	private final String department;
	private final String position;
	private final String role;
	private final String phoneNumber;
	private final String email;
	private final String password;
	private final boolean deleted;
	private final LocalDateTime birthday;

	public MemberUpdateRequest toServiceDto() {
		return new MemberUpdateRequest(
			this.id, this.companyId, this.name, this.department, this.position,
			this.role, this.phoneNumber, this.email, this.password, this.birthday, this.deleted
		);
	}
}
