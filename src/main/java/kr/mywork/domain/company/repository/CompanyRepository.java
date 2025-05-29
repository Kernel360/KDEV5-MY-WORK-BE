package kr.mywork.domain.company.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.mywork.domain.company.model.Company;
import kr.mywork.domain.company.service.dto.request.CompanyCreateRequest;
import kr.mywork.domain.company.service.dto.response.CompanySelectResponse;

public interface CompanyRepository {
	Company save(CompanyCreateRequest companyCreateRequest);

	Optional<Company> findById(UUID companyId);

	List<CompanySelectResponse> findCompaniesBySearchConditionWithPaging(int page, int companySize, String companyType,
		String keyword, Boolean deleted);

	Long countTotalCompaniesByCondition(String companyType, String keyword, Boolean deleted);
}
