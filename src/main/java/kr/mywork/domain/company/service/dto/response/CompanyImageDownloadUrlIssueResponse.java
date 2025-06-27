package kr.mywork.domain.company.service.dto.response;

import java.util.UUID;

public record CompanyImageDownloadUrlIssueResponse(UUID companyId, String downloadUrl) {
}
