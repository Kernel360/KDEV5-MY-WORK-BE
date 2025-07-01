package kr.mywork.interfaces.company.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.company.service.dto.response.CompanySelectResponse;

public record CompanySelectWebResponse(UUID companyId, String companyName, String businessNumber, String address,
									   String contactPhoneNumber, Boolean deleted,
									   @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime createdAt
) {
	public static CompanySelectWebResponse from(CompanySelectResponse companySelectResponse) {
		return new CompanySelectWebResponse(
			companySelectResponse.companyId(),
			companySelectResponse.companyName(),
			companySelectResponse.businessNumber(),
			companySelectResponse.address(),
			companySelectResponse.contactPhoneNumber(),
			companySelectResponse.deleted(),
			companySelectResponse.createdAt()
		);
	}
}
