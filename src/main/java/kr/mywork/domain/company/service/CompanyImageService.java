package kr.mywork.domain.company.service;

import java.net.URL;
import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.domain.company.errors.CompanyErrorType;
import kr.mywork.domain.company.errors.CompanyIdNotFoundException;
import kr.mywork.domain.company.errors.CompanyImageAlreadyExistException;
import kr.mywork.domain.company.errors.CompanyNotFoundException;
import kr.mywork.domain.company.model.Company;
import kr.mywork.domain.company.model.CompanyId;
import kr.mywork.domain.company.repository.CompanyIdRepository;
import kr.mywork.domain.company.repository.CompanyRepository;
import kr.mywork.domain.company.service.dto.response.CompanyImageDeleteResponse;
import kr.mywork.domain.company.service.dto.response.CompanyImageDownloadUrlIssueResponse;
import kr.mywork.domain.company.service.dto.response.CompanyImageUploadUrlIssueResponse;
import kr.mywork.domain.company.uploader.CompanyImageFileHandler;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyImageService {

	private final CompanyIdRepository companyIdRepository;
	private final CompanyRepository companyRepository;
	private final CompanyImageFileHandler companyImageFileHandler;

	@Transactional(readOnly = true)
	public CompanyImageUploadUrlIssueResponse issueCompanyImageUploadUrl(final UUID companyId, final String fileName) {
		final Optional<Company> companyOptional = companyRepository.findById(companyId);

		return companyOptional.map(company -> uploadCompanyImage(fileName, company))
			.orElseGet(() -> uploadNewCompanyImage(companyId, fileName));
	}

	private CompanyImageUploadUrlIssueResponse uploadCompanyImage(final String fileName, final Company company) {
		if (company.existsImage()) {
			throw new CompanyImageAlreadyExistException(CompanyErrorType.COMPANY_IMAGE_EXIST);
		}

		final URL uploadUrl = companyImageFileHandler.createUploadUrl(company.getId(), fileName);
			return new CompanyImageUploadUrlIssueResponse(company.getId(), uploadUrl.toString());
	}

	private CompanyImageUploadUrlIssueResponse uploadNewCompanyImage(final UUID companyId, final String fileName) {

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

	@Transactional
	public CompanyImageDeleteResponse deleteImage(final UUID companyId) {
		final Optional<Company> companyOptional = companyRepository.findById(companyId);

		return companyOptional.map(this::deleteLogoImage)
			.orElseGet(() -> deleteNewLogoImage(companyId));
	}

	private CompanyImageDeleteResponse deleteLogoImage(final Company company) {
		companyImageFileHandler.deleteImage(company.getId());

		final boolean deleted = company.deleteImage();

		return new CompanyImageDeleteResponse(company.getId(), deleted);
	}

	private CompanyImageDeleteResponse deleteNewLogoImage(final UUID companyId) {
		final CompanyId issuedCompanyId = companyIdRepository.findById(companyId)
			.orElseThrow(() -> new CompanyIdNotFoundException(CompanyErrorType.ID_NOT_FOUND));

		companyImageFileHandler.deleteImage(issuedCompanyId.getId());

		return new CompanyImageDeleteResponse(issuedCompanyId.getId(), true);
	}
}
