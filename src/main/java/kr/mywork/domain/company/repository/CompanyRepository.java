package kr.mywork.domain.company.repository;

import java.util.UUID;
import kr.mywork.domain.company.model.Company;
import kr.mywork.domain.company.service.dto.request.CompanyCreateRequest;

public interface CompanyRepository {
	Company save(CompanyCreateRequest companyCreateRequest);

	Company findById(UUID id);
}
