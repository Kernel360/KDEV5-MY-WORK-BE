package kr.mywork.domain.company.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {

	@Id
	@Getter
	private UUID id;

	@Column(nullable = false, length = 30)
	private String name;

	@Column(nullable = false, length = 300)
	private String detail;

	@Column(nullable = false, unique = true, length = 100)
	private String businessNumber;

	@Column(nullable = false, length = 300)
	private String address;

	@Column(nullable = false, length = 10)
	@Enumerated(value = jakarta.persistence.EnumType.STRING)
	private CompanyType type;

	@Column(nullable = false, length = 50)
	private String contactPhoneNumber;

	@Column(nullable = false, length = 300)
	private String contactEmail;

	@Column
	private String logoImagePath;

	@Column(nullable = false, columnDefinition = "timestamp")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(nullable = false, columnDefinition = "timestamp")
	@UpdateTimestamp
	private LocalDateTime modifiedAt;

	@Setter
	private Boolean deleted = false;

	public Company(final UUID id, final String name, final String detail, final String businessNumber,
		final String address, final String type, final String contactPhoneNumber, final String contactEmail,
		final String logoImagePath) {
		this.id = id;
		this.name = name;
		this.detail = detail;
		this.businessNumber = businessNumber;
		this.address = address;
		this.type = CompanyType.from(type);
		this.contactPhoneNumber = contactPhoneNumber;
		this.contactEmail = contactEmail;
		this.logoImagePath = logoImagePath;
	}

}
