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
import kr.mywork.domain.company.service.dto.response.CompanyNameResponse;
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
	public List<CompanySelectResponse> findCompaniesBySearchConditionWithPaging(final int page,
		final int companyPageSize,
		final String companyType,
		final String keywordType,
		final String keyword,
		final Boolean deleted) {
		final int offset = (page - 1) * companyPageSize;

		return queryFactory.select(Projections.constructor(CompanySelectResponse.class,
				company.id,
				company.name,
				company.businessNumber,
				company.address,
				company.contactPhoneNumber,
				company.deleted,
				company.createdAt))
			.from(company)
			.where(
				eqCompanyType(companyType),
				eqDeleted(deleted),
				containsKeyword(keywordType, keyword)
			)
			.orderBy(company.createdAt.desc())
			.offset(offset)
			.limit(companyPageSize)
			.fetch();
	}

	private BooleanExpression containsKeyword(final String searchType, final String keyword) {
		if (searchType == null) {
			return null;
		}

		if (keyword == null || keyword.isEmpty()) {
			return null;
		}

		return switch (searchType) {
			case "NAME" -> company.name.containsIgnoreCase(keyword);
			case "BUSINESS_NUMBER" -> company.businessNumber.containsIgnoreCase(keyword);
			case "PHONE_NUMBER" -> company.contactPhoneNumber.containsIgnoreCase(keyword);
			case "ADDRESS" -> company.address.containsIgnoreCase(keyword);
			default -> null;
		};
	}

	@Override
	public Long countTotalCompaniesByCondition(final String companyType, String keywordType, final String keyword,
		final Boolean deleted) {
		return queryFactory.select(company.id.count())
			.from(company)
			.where(
				eqCompanyType(companyType),
				eqDeleted(deleted),
				containsKeyword(keywordType, keyword))
			.fetchOne();
	}

	private BooleanExpression eqDeleted(Boolean deleted) {
		if (deleted == null) {
			return null;
		}

		return company.deleted.eq(deleted);
	}

	@Override
	public List<CompanyNameResponse> findCompanyNamesByCompanyType(final String companyType) {
		return queryFactory
			.select(Projections.constructor(
				CompanyNameResponse.class,
				company.id,
				company.name))
			.from(company)
			.where(
				eqCompanyType(companyType),
				company.deleted.eq(false)
			)
			.fetch();
	}

	@Override
	public List<Company> findAllByIds(final List<UUID> devCompanyIds) {
		return queryFactory.selectFrom(company)
			.where(company.id.in(devCompanyIds))
			.fetch();
	}

	@Override
	public List<Company> findAllByNameAndType(final String companyName, final String type) {
		return queryFactory.selectFrom(company)
			.where(company.name.contains(companyName), company.type.stringValue().eq(type))
			.fetch();
	}

	@Override
	public boolean existsByBusinessNumber(final String businessNumber) {
		return queryFactory.select(company.id)
			.from(company)
			.where(company.businessNumber.eq(businessNumber))
			.fetchFirst() != null;
	}

	public BooleanExpression eqCompanyType(final String companyType) {
		return (companyType == null) ? null : company.type.stringValue().eq(companyType);
	}
}
