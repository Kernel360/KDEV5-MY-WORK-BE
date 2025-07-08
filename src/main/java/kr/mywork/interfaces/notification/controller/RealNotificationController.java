package kr.mywork.interfaces.notification.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import kr.mywork.common.auth.components.annotation.LoginMember;
import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.notification.service.NotificationService;
import kr.mywork.domain.notification.service.RealTimeNotificationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RealNotificationController {

	private final RealTimeNotificationService realTimeNotificationService;
	private final NotificationService notificationService;

	@GetMapping( value = "/real-notifications/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<SseEmitter> connectForRealTimeNotification(@LoginMember LoginMemberDetail loginMemberDetail) {
		final SseEmitter sseEmitter = realTimeNotificationService.addSseEmitter(loginMemberDetail.memberId());
		long count = notificationService.countUnreadNotifications(loginMemberDetail.memberId());
		realTimeNotificationService.sendNotification(loginMemberDetail.memberId(), "notification-unread-count", count);
		return ResponseEntity.ok(sseEmitter);
	}

}
