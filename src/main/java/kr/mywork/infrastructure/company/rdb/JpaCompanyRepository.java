package kr.mywork.infrastructure.company.rdb;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mywork.domain.company.model.Company;
import kr.mywork.domain.company.model.CompanyType;

public interface JpaCompanyRepository extends JpaRepository<Company, UUID> {
	Optional<Company> findByIdAndType(UUID companyId, CompanyType type);
}
