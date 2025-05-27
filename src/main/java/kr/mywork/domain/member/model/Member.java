package kr.mywork.domain.member.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	@Id
	@Getter
	private UUID id;

	@Column(nullable = false, length = 30)
	private String name;

	@Column(nullable = false, length = 30)
	private String department;

	@Column(nullable = false, length = 30)
	private String postion;

	@Column(nullable = false, length = 30)
	private String role;

	@Column(nullable = false, length = 50)
	private String phoneNmeber;

	@Column(nullable = false, length = 300)
	private String email;

	private String password;

	private Boolean deleted = false;

	@Column(nullable = false, columnDefinition = "timestamp")
	private LocalDateTime birthDate;

}
