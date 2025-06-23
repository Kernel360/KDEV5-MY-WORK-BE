package kr.mywork.domain.company.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.uuid.Generators;

import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.activityLog.listener.eventObject.CreateEventObject;
import kr.mywork.domain.activityLog.listener.eventObject.DeleteEventObject;
import kr.mywork.domain.activityLog.listener.eventObject.ModifyEventObject;
import kr.mywork.domain.company.errors.CompanyErrorType;
import kr.mywork.domain.company.errors.CompanyIdNotFoundException;
import kr.mywork.domain.company.errors.CompanyNotFoundException;
import kr.mywork.domain.company.model.Company;
import kr.mywork.domain.company.repository.CompanyIdRepository;
import kr.mywork.domain.company.repository.CompanyRepository;
import kr.mywork.domain.company.service.dto.request.CompanyCreateRequest;
import kr.mywork.domain.company.service.dto.request.CompanyUpdateRequest;
import kr.mywork.domain.company.service.dto.response.CompanyDetailResponse;
import kr.mywork.domain.company.service.dto.response.CompanyNameResponse;
import kr.mywork.domain.company.service.dto.response.CompanySelectResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {

	@Value("${company.page.size}")
	private int companyPageSize;

	private final CompanyRepository companyRepository;
	private final CompanyIdRepository companyIdRepository;
	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public UUID createCompanyId() {
		final UUID companyId = Generators.timeBasedEpochGenerator().generate();
		return companyIdRepository.save(companyId);
	}

	@Transactional
	public UUID createCompany(final CompanyCreateRequest companyCreateRequest, LoginMemberDetail loginMemberDetail) {
		companyIdRepository.findById(companyCreateRequest.getId())
			.orElseThrow(() -> new CompanyIdNotFoundException(CompanyErrorType.ID_NOT_FOUND));

		final Company savedCompany = companyRepository.save(companyCreateRequest);

		eventPublisher.publishEvent(new CreateEventObject(savedCompany, loginMemberDetail));

		return savedCompany.getId();
	}

	@Transactional
	public UUID deleteCompany(final UUID companyId, LoginMemberDetail loginMemberDetail) {
		Company company = companyRepository.findById(companyId)
			.orElseThrow(() -> new CompanyNotFoundException(CompanyErrorType.COMPANY_NOT_FOUND));

		company.setDeleted(true);

		eventPublisher.publishEvent(new DeleteEventObject(company, loginMemberDetail));

		return company.getId();
	}

	@Transactional
	public UUID updateCompany(CompanyUpdateRequest companyUpdateRequest, LoginMemberDetail loginMemberDetail) {
		Company company = companyRepository.findById(companyUpdateRequest.getId())
			.orElseThrow(() -> new CompanyNotFoundException(CompanyErrorType.COMPANY_NOT_FOUND));

		Company before = Company.copyOf(company);

		company.updateFrom(companyUpdateRequest);

		eventPublisher.publishEvent(new ModifyEventObject(before, company, loginMemberDetail));

		return company.getId();
	}

	@Transactional(readOnly = true)
	public CompanyDetailResponse findCompanyById(UUID companyId) {
		final Company company = companyRepository.findById(companyId)
			.orElseThrow(() -> new CompanyNotFoundException(CompanyErrorType.COMPANY_NOT_FOUND));

		return CompanyDetailResponse.fromEntity(company);
	}

	@Transactional(readOnly = true)
	public List<CompanySelectResponse> findCompaniesBySearchConditionWithPaging(final int page,
		String companyType, String keywordType, String keyword, Boolean deleted) {
		return companyRepository.findCompaniesBySearchConditionWithPaging(
			page, companyPageSize, companyType, keywordType, keyword, deleted);
	}

	@Transactional(readOnly = true)
	public Long countTotalCompaniesByCondition(String companyType, String keywordType, String keyword, Boolean deleted) {
		return companyRepository.countTotalCompaniesByCondition(companyType, keywordType, keyword, deleted);
	}
	@Transactional(readOnly = true)
	public List<CompanyNameResponse> findCompanyNamesByCompanyType(final String companyType){
		return companyRepository.findCompanyNamesByCompanyType(companyType);
	}

}
