package kr.mywork.infrastructure.company.rdb;

import static kr.mywork.domain.company.model.QCompanyId.companyId;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.mywork.domain.company.model.CompanyId;
import kr.mywork.domain.company.repository.CompanyIdRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryDslCompanyIdRepository implements CompanyIdRepository {

	private final JPAQueryFactory queryFactory;
	private final JpaCompanyIdRepository jpaCompanyIdRepository;

	@Override
	public UUID save(final UUID companyId) {
		return jpaCompanyIdRepository.save(new CompanyId(companyId)).getId();
	}

	@Override
	public Optional<CompanyId> findById(final UUID id) {
		final CompanyId findCompanyId = queryFactory.select(companyId)
			.from(companyId)
			.where(companyId.id.eq(id))
			.fetchFirst();

		return Optional.ofNullable(findCompanyId);
	}

}
