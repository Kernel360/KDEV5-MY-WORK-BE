package kr.mywork.domain.company.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.mywork.domain.company.model.Company;
import kr.mywork.domain.company.model.CompanyType;
import kr.mywork.domain.company.service.dto.request.CompanyCreateRequest;
import kr.mywork.domain.company.service.dto.response.CompanyNameResponse;
import kr.mywork.domain.company.service.dto.response.CompanySelectResponse;

public interface CompanyRepository {
	Company save(CompanyCreateRequest companyCreateRequest);

	Optional<Company> findById(UUID companyId);

	List<CompanySelectResponse> findCompaniesBySearchConditionWithPaging(int page, int companySize,
		final String companyType, String keywordType, String keyword, Boolean deleted);

	Long countTotalCompaniesByCondition(String companyType, String keywordType, String keyword, Boolean deleted);

	Optional<Company> findByIdAndType(UUID companyId, CompanyType type);

	List<CompanyNameResponse> findCompanyNamesByCompanyType(String companyType);

	List<Company> findAllByIds(List<UUID> devCompanyIds);

	List<Company> findAllByNameAndType(String companyName, String type);

	boolean existsByBusinessNumber(String businessNumber);
}
