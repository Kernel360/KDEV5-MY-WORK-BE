package kr.mywork.domain.company.service;

import java.net.URL;
import java.time.Duration;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.domain.company.errors.CompanyErrorType;
import kr.mywork.domain.company.errors.CompanyIdNotFoundException;
import kr.mywork.domain.company.errors.CompanyNotFoundException;
import kr.mywork.domain.company.model.Company;
import kr.mywork.domain.company.model.CompanyId;
import kr.mywork.domain.company.repository.CompanyIdRepository;
import kr.mywork.domain.company.repository.CompanyRepository;
import kr.mywork.domain.company.service.dto.response.CompanyImageDownloadUrlIssueResponse;
import kr.mywork.domain.company.service.dto.response.CompanyImageUploadUrlIssueResponse;
import kr.mywork.domain.company.uploader.CompanyImageFileHandler;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyImageService {

	private final CompanyIdRepository companyIdRepository;
	private final CompanyImageFileHandler companyImageFileHandler;

	@Transactional(readOnly = true)
	public CompanyImageUploadUrlIssueResponse issueCompanyImageUploadUrl(final UUID companyId, final String fileName) {
		final CompanyId issuedCompanyId = companyIdRepository.findById(companyId)
			.orElseThrow(() -> new CompanyIdNotFoundException(CompanyErrorType.ID_NOT_FOUND));

		final URL uploadUrl = companyImageFileHandler.createUploadUrl(issuedCompanyId.getId(), fileName);

		return new CompanyImageUploadUrlIssueResponse(issuedCompanyId.getId(), uploadUrl.toString());
	}

	@Transactional
	public CompanyImageDownloadUrlIssueResponse downloadCompanyImage(final UUID companyId) {
		final Company company = companyRepository.findById(companyId)
			.orElseThrow(() -> new CompanyNotFoundException(CompanyErrorType.COMPANY_NOT_FOUND));

		final URL downloadUrl = companyImageFileHandler.issueDownloadUrl(company.getFilePath(), Duration.ofMinutes(3));

		return new CompanyImageDownloadUrlIssueResponse(company.getId(), downloadUrl.toString());
	}
}
