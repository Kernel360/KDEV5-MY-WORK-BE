package kr.mywork.infrastructure.notification.rdb;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mywork.domain.notification.model.Notification;

public interface JpaNotificationRepository extends JpaRepository<Notification, UUID> {
}
