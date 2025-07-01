package kr.mywork.domain.company.service.dto.response;

import java.util.UUID;

public record CompanyImageDeleteResponse(UUID companyId, boolean deleted) {
}
