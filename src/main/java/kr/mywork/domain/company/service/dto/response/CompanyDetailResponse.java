package kr.mywork.domain.company.service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import kr.mywork.domain.company.model.Company;

public record CompanyDetailResponse(UUID id, String name, String detail, String businessNumber, String address,
									String type, String contactPhoneNumber, String contactEmail, String logoImagePath,
									LocalDateTime createdAt, LocalDateTime modifiedAt) {

	public static CompanyDetailResponse fromEntity(final Company company) {
		return new CompanyDetailResponse(
			company.getId(),
			company.getName(),
			company.getDetail(),
			company.getBusinessNumber(),
			company.getAddress(),
			company.getType().name(),
			company.getContactPhoneNumber(),
			company.getContactEmail(),
			company.getLogoImagePath(), // TODO host name 을 가져오는 로직 필요
			company.getCreatedAt(),
			company.getModifiedAt()
		);
	}
}
