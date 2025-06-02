package kr.mywork.domain.member.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

	@Id
	private UUID id;

	@Column(nullable = false)
	private UUID companyId;

	@Column(length = 30, nullable = false)
	private String name;

	@Column(length = 100)
	private String department;

	@Column(length = 50)
	private String position;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MemberRole role;

	@Column(length = 300)
	private String phoneNumber;

	@Column(length = 300, unique = true, nullable = false)
	private String email;

	@Column(length = 300, nullable = false)
	private String password;

	@Column(nullable = false)
	private boolean deleted;
}
