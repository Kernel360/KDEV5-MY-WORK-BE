package kr.mywork.domain.company.service.dto.response;

import java.time.LocalDateTime;

public record CompanySelectResponse(String companyName, String businessNumber, String address,
									String contactPhoneNumber, Boolean deleted, LocalDateTime createdAt) {
}
