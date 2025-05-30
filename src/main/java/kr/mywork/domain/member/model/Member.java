package kr.mywork.domain.member.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

	@Id
	private UUID id;

	@Column(name = "company_id", nullable = false)
	private UUID companyId;

	@Column(name = "name", length = 30, nullable = false)
	private String name;

	@Column(name = "department", length = 100)
	private String department;

	@Column(name = "position", length = 50)
	private String position;

	@Column(name = "role", length = 20)
	@Enumerated(value = jakarta.persistence.EnumType.STRING)
	private MemberType role;

	@Column(name = "phone_number", length = 300)
	private String phoneNumber;

	@Column(name = "email", length = 300, unique = true, nullable = false)
	private String email;

	@Column(name = "password", length = 300, nullable = false)
	private String password;

	@Column(name = "deleted", nullable = false)
	private boolean deleted;
}
