package kr.mywork.domain.company.service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CompanySelectResponse(UUID companyId, String companyName, String businessNumber, String address,
									String contactPhoneNumber, Boolean deleted, LocalDateTime createdAt) {
}
