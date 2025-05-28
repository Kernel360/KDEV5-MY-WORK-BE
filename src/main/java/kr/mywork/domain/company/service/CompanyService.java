package kr.mywork.domain.company.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.uuid.Generators;

import kr.mywork.domain.company.errors.CompanyErrorType;
import kr.mywork.domain.company.errors.CompanyIdNotFoundException;
import kr.mywork.domain.company.errors.CompanyNotFoundException;
import kr.mywork.domain.company.model.Company;
import kr.mywork.domain.company.repository.CompanyIdRepository;
import kr.mywork.domain.company.repository.CompanyRepository;
import kr.mywork.domain.company.service.dto.request.CompanyCreateRequest;
import kr.mywork.domain.company.service.dto.request.CompanyUpdateRequest;
import kr.mywork.domain.company.service.dto.response.CompanyDetailResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {

	private final CompanyRepository companyRepository;
	private final CompanyIdRepository companyIdRepository;

	@Transactional
	public UUID createCompanyId() {
		final UUID companyId = Generators.timeBasedEpochGenerator().generate();
		return companyIdRepository.save(companyId);
	}

	@Transactional
	public UUID createCompany(final CompanyCreateRequest companyCreateRequest) {
		companyIdRepository.findById(companyCreateRequest.getId())
			.orElseThrow(() -> new CompanyIdNotFoundException(CompanyErrorType.ID_NOT_FOUND));

		final Company savedCompany = companyRepository.save(companyCreateRequest);
		return savedCompany.getId();
	}

	@Transactional
	public UUID deleteCompany(final UUID companyId) {
		Company company= companyRepository.findById(companyId)
				.orElseThrow(() -> new CompanyNotFoundException(CompanyErrorType.COMPANY_NOT_FOUND));

		company.setDeleted(true);

		return company.getId();
	}

	@Transactional
	public UUID updateCompany(CompanyUpdateRequest companyUpdateRequest) {
		Company company = companyRepository.findById(companyUpdateRequest.getId())
				.orElseThrow(() -> new CompanyNotFoundException(CompanyErrorType.COMPANY_NOT_FOUND));

		company.updateFrom(companyUpdateRequest);
		return company.getId();
	}

	@Transactional(readOnly = true)
	public CompanyDetailResponse findCompanyById(UUID companyId) {
		final Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new CompanyNotFoundException(CompanyErrorType.COMPANY_NOT_FOUND));

		return CompanyDetailResponse.fromEntity(company);
	}
}
