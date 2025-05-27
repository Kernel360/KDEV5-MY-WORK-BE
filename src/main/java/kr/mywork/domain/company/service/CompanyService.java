package kr.mywork.domain.company.service;

import java.util.List;
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
import kr.mywork.domain.company.service.dto.request.CompanyDetailResquest;
import kr.mywork.domain.company.service.dto.request.CompanyUpdateRequest;
import kr.mywork.domain.company.service.dto.response.CompanyDetailResponse;
import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {

	private final CompanyRepository companyRepository;
	private final CompanyIdRepository companyIdRepository;
	private final MemberRepository memberRepositroy;


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

	@Transactional
	public CompanyDetailResponse searchCompanyDetail (final UUID companyId){
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new CompanyNotFoundException(CompanyErrorType.COMPANY_NOT_FOUND));
		
		//멤버 정보에 대한 엔티티 기준으로 멤버 조회 
		List<Member> companyMember = memberRepositroy.findAllMemberByCompanyId();


		return CompanyDetailResponse.from(company, companyMember);
	}
}
