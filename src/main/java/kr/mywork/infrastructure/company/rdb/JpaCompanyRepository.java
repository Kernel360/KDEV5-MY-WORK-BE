package kr.mywork.infrastructure.company.rdb;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mywork.domain.company.model.Company;

import java.util.UUID;

public interface JpaCompanyRepository extends JpaRepository<Company, UUID> {
}
