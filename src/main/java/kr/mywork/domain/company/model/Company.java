package kr.mywork.domain.company.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import kr.mywork.domain.company.errors.CompanyErrorType;
import kr.mywork.domain.company.errors.CompanyImageAlreadyExistException;
import kr.mywork.domain.company.errors.CompanyImageEmptyException;
import kr.mywork.domain.company.service.dto.request.CompanyUpdateRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Company {

	@Id
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
	private String fileName;

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
		final String fileName) {
		this.id = id;
		this.name = name;
		this.detail = detail;
		this.businessNumber = businessNumber;
		this.address = address;
		this.type = CompanyType.from(type);
		this.contactPhoneNumber = contactPhoneNumber;
		this.contactEmail = contactEmail;
		this.fileName = fileName;
	}

	public static Company copyOf(Company company) {
		return new Company(
			company.id,
			company.name,
			company.detail,
			company.businessNumber,
			company.address,
			company.type.toString(),
			company.contactPhoneNumber,
			company.contactEmail,
			company.fileName
		);
	}

	public void updateFrom(CompanyUpdateRequest companyUpdateRequest) {
		this.id = companyUpdateRequest.getId();
		this.name = companyUpdateRequest.getName();
		this.detail = companyUpdateRequest.getDetail();
		this.businessNumber = companyUpdateRequest.getBusinessNumber();
		this.address = companyUpdateRequest.getAddress();
		this.type = CompanyType.from(companyUpdateRequest.getType());
		this.contactPhoneNumber = companyUpdateRequest.getContactPhoneNumber();
		this.contactEmail = companyUpdateRequest.getContactEmail();
		this.fileName = companyUpdateRequest.getLogoImagePath();
	}

	public String getFilePath() {
		return String.format("/%s/%s", id, fileName);
	}

	public boolean deleteImage() {
		if (this.fileName == null) {
			throw new CompanyImageEmptyException(CompanyErrorType.COMPANY_IMAGE_EMPTY);
		}

		this.fileName = null;
		return true;
	}

	public boolean assignLogoImage(final String fileName) {
		if (this.fileName == null || this.fileName.isEmpty()) {
			this.fileName = fileName;
			return true;
		}

		throw new CompanyImageAlreadyExistException(CompanyErrorType.COMPANY_IMAGE_EXIST);
	}
}
