package kr.mywork.domain.member.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import kr.mywork.common.rdb.id.UnixTimeOrderedUuidGeneratedValue;
import kr.mywork.domain.member.service.dto.request.MemberUpdateRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

	@Id
	@UnixTimeOrderedUuidGeneratedValue
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

	@Column(nullable = false, columnDefinition = "timestamp")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(nullable = false, columnDefinition = "timestamp")
	@UpdateTimestamp
	private LocalDateTime modifiedAt;

	@Column(nullable = false, columnDefinition = "timestamp")
	private LocalDateTime birthDate;

	public Member(final UUID companyId, final String name, final String department,
		final String position, final String role, final String phoneNumber, final String email, final String password,
		final LocalDateTime birthDate) {
		this.companyId = companyId;
		this.name = name;
		this.department = department;
		this.position = position;
		this.role = MemberRole.from(role);
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
	}

	// 소프트 딜리트 전용 메서드
	public void softDelete() {
		this.deleted = true; // ← 더티 체킹 대상
	}
	public void setPasswordEncode(String encodedPassword) {
		this.password = encodedPassword;
	}

	//관리자용 회원정보 수정
	public void updateFrom(MemberUpdateRequest memberUpdateRequest) {
		this.companyId = memberUpdateRequest.getCompanyId();
		this.name = memberUpdateRequest.getName();
		this.department = memberUpdateRequest.getDepartment();
		this.position = memberUpdateRequest.getPosition();
		this.role = MemberRole.from(memberUpdateRequest.getRole());
		this.phoneNumber = memberUpdateRequest.getPhoneNumber();
		this.email = memberUpdateRequest.getEmail();
		this.password = memberUpdateRequest.getPassword();
		this.birthDate = memberUpdateRequest.getBirthday();
		this.deleted = memberUpdateRequest.isDeleted();
	}
}
