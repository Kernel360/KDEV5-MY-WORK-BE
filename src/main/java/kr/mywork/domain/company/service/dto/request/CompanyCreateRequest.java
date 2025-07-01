package kr.mywork.domain.company.service.dto.request;

import java.util.UUID;

import kr.mywork.domain.company.model.Company;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyCreateRequest {
	@Getter
	private final UUID id;
	private final String name;
	private final String detail;
	@Getter
	private final String businessNumber;
	private final String address;
	private final String type;
	private final String contactPhoneNumber;
	private final String contactEmail;
	private final String logoImagePath;

	public Company toEntity() {
		return new Company(this.id, this.name, this.detail, this.businessNumber, this.address, this.type,
			this.contactPhoneNumber, this.contactEmail, logoImagePath);
	}
}
