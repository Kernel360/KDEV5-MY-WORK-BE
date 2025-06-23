package kr.mywork.domain.company.service.dto.response;

import java.util.UUID;

public record CompanyImageUploadUrlIssueResponse(UUID companyId, String uploadUrl) {
}
