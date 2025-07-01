package kr.mywork.domain.company.repository;

import java.util.Optional;
import java.util.UUID;

import kr.mywork.domain.company.model.CompanyId;

public interface CompanyIdRepository {
	UUID save(UUID companyId);

	Optional<CompanyId> findById(UUID id);
}
