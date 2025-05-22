package kr.mywork.interfaces.company.controller.dto.request;

import java.util.Objects;
import java.util.UUID;

import kr.mywork.domain.company.model.CompanyType;
import kr.mywork.domain.company.service.dto.request.CompanyCreateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class CompanyCreateWebRequest {
	private final UUID id;
	private final String name;
	private final String detail;
	private final String businessNumber;
	private final String address;
	private final String type;
	private final String contactPhoneNumber;
	private final String contactEmail;
	private final String logoImagePath;

	public CompanyCreateRequest toServiceDto() {
		return new CompanyCreateRequest(this.id, this.name, this.detail, this.businessNumber, this.address,
			this.type, this.contactPhoneNumber, this.contactEmail,
			this.logoImagePath);
	}
}
