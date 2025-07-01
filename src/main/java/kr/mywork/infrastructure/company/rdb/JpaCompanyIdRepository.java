package kr.mywork.infrastructure.company.rdb;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mywork.domain.company.model.CompanyId;

public interface JpaCompanyIdRepository extends JpaRepository<CompanyId, UUID> {
}
