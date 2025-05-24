package kr.mywork.domain.company.repository;

import kr.mywork.domain.company.model.Company;
import kr.mywork.domain.company.service.dto.request.CompanyCreateRequest;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository {
	Company save(CompanyCreateRequest companyCreateRequest);

	Optional<Company> findById(UUID companyId);
}
