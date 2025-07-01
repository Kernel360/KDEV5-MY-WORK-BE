package kr.mywork.infrastructure.notification.rdb;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.mywork.domain.notification.errors.NotificationErrorType;
import kr.mywork.domain.notification.errors.NotificationException;
import kr.mywork.domain.notification.model.Notification;
import kr.mywork.domain.notification.repository.NotificationRepository;
import kr.mywork.domain.notification.service.dto.response.NotificationSelectResponse;
import lombok.RequiredArgsConstructor;

import static kr.mywork.domain.notification.model.QNotification.notification;

@Repository
@RequiredArgsConstructor
public class QueryDslNotificationRepository implements NotificationRepository {
	@Value("${post.page.size}")
	private int postPageSize;

	private final JpaNotificationRepository jpaNotificationRepository;
	private final JPAQueryFactory queryFactory;

	public Notification save(Notification notification) {
		return jpaNotificationRepository.save(notification);
	}

 	public Notification findById(UUID id) {
		return jpaNotificationRepository.findById(id)
			.orElseThrow(() -> new NotificationException(NotificationErrorType.NOTIFICATION_NOT_FOUND) {
			});
	}

	@Override
	public List<NotificationSelectResponse> findByConditionWithPaging(int page, Boolean isRead, UUID memberId) {

		final int offset = (page - 1) * postPageSize;

		return queryFactory
			.select(Projections.constructor(
				NotificationSelectResponse.class,
				notification.id,
				notification.actorId,
				notification.actorName,
				notification.actionType,
				notification.targetType,
				notification.targetId,
				notification.notificationContent,
				notification.projectId,
				notification.projectStepId,
				notification.actionDate,
				notification.isRead
			))
			.from(notification)
			.where(
				notification.receiverMemberId.eq(memberId),
				eqRead(isRead)
			)
			.orderBy(notification.actionDate.desc())
			.offset(offset)
			.limit(postPageSize)
			.fetch();
	}

	private BooleanExpression eqRead(Boolean isRead) {
		if (isRead == null) {
			return null;
		}

		return notification.isRead.eq(isRead);
	}

	@Override
	public long countByMemberIdAndIsReadFalse(UUID memberId) {
		return queryFactory
			.select(notification.count())
			.from(notification)
			.where(
				notification.receiverMemberId.eq(memberId),
				notification.isRead.isFalse()
			)
			.fetchOne();
	}

}
