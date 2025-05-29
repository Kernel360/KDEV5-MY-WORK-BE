package kr.mywork.interfaces.company.controller.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.mywork.domain.company.service.dto.response.CompanySelectResponse;

public record CompanySelectWebResponse(String companyName, String businessNumber, String address,
									   String contactPhoneNumber, Boolean deleted,
									   @JsonFormat(pattern = "yyyy-MM-dd") LocalDateTime createdAt
) {
	public static CompanySelectWebResponse from(CompanySelectResponse companySelectResponse) {
		return new CompanySelectWebResponse(
			companySelectResponse.companyName(),
			companySelectResponse.businessNumber(),
			companySelectResponse.address(),
			companySelectResponse.contactPhoneNumber(),
			companySelectResponse.deleted(),
			companySelectResponse.createdAt()
		);
	}
}
