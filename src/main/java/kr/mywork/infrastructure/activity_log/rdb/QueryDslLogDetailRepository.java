package kr.mywork.infrastructure.activity_log.rdb;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.transaction.Transactional;
import kr.mywork.domain.activityLog.model.LogDetail;
import kr.mywork.domain.activityLog.repository.LogDetailRepository;
import lombok.RequiredArgsConstructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class QueryDslLogDetailRepository implements LogDetailRepository {

	private final JpaLogDetailRepository jpaLogDetailRepository;
	private final JPAQueryFactory jpaQueryFactory;

	public void saveAll(final List<LogDetail> logDetails) {
		jpaLogDetailRepository.saveAll(logDetails);
	}

	public List<LogDetail> findByActivityLogId(UUID id) {
		return jpaLogDetailRepository.findByActivityLogId(id);
	}

}
