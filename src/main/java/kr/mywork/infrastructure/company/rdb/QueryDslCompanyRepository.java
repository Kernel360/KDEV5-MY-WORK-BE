package kr.mywork.infrastructure.company.rdb;

import org.springframework.stereotype.Repository;

import kr.mywork.domain.company.model.Company;
import kr.mywork.domain.company.repository.CompanyRepository;
import kr.mywork.domain.company.service.dto.request.CompanyCreateRequest;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryDslCompanyRepository implements CompanyRepository {

	private final JpaCompanyRepository companyRepository;

	@Override
	public Company save(final CompanyCreateRequest companyCreateRequest) {
		return companyRepository.save(companyCreateRequest.toEntity());
	}
}
