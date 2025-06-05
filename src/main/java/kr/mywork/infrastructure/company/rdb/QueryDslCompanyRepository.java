package kr.mywork.infrastructure.company.rdb;

import static kr.mywork.domain.company.model.QCompany.company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.mywork.domain.company.model.Company;
import kr.mywork.domain.company.model.CompanyType;
import kr.mywork.domain.company.repository.CompanyRepository;
import kr.mywork.domain.company.service.dto.request.CompanyCreateRequest;
import kr.mywork.domain.company.service.dto.response.CompanySelectResponse;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Transactional
public class QueryDslCompanyRepository implements CompanyRepository {

	private final JpaCompanyRepository companyRepository;
	private final JPAQueryFactory queryFactory;

	@Override
	public Company save(final CompanyCreateRequest companyCreateRequest) {
		return companyRepository.save(companyCreateRequest.toEntity());
	}

	@Override
	public Optional<Company> findById(UUID companyId) {
		return companyRepository.findById(companyId);
	}

	public Optional<Company> findByIdAndType(UUID companyId, CompanyType type) {
		return companyRepository.findByIdAndType(companyId, type);
	}

	@Override
	public List<CompanySelectResponse> findCompaniesBySearchConditionWithPaging(final int page, final int companyPageSize,
		final String companyType, String keyword, Boolean deleted) {

		final int offset = (page - 1) * companyPageSize;

		return queryFactory.select(Projections.constructor(CompanySelectResponse.class,
				company.name,
				company.businessNumber,
				company.address,
				company.contactPhoneNumber,
				company.deleted,
				company.createdAt))
			.from(company)
			.where(
				company.type.stringValue().eq(companyType),
				eqDeleted(deleted),
				eqKeyword(keyword))
			.offset(offset)
			.limit(companyPageSize)
			.fetch();
	}

	@Override
	public Long countTotalCompaniesByCondition(final String companyType, final String keyword, final Boolean deleted) {
		return queryFactory.select(company.id.count())
			.from(company)
			.where(
				company.type.stringValue().eq(companyType),
				eqDeleted(deleted),
				eqKeyword(keyword))
			.fetchOne();
	}

	private BooleanExpression eqDeleted(Boolean deleted) {
		if (deleted == null) {
			return null;
		}

		return company.deleted.eq(deleted);
	}

	private BooleanExpression eqKeyword(String keyword) {
		if (keyword == null) {
			return null;
		}

		return company.name.like(keyword + "%");
	}
}
