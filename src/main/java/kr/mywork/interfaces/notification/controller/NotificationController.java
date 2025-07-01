package kr.mywork.interfaces.notification.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.common.auth.components.annotation.LoginMember;
import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.notification.service.NotificationService;
import kr.mywork.domain.notification.service.dto.response.NotificationReadResponse;
import kr.mywork.domain.notification.service.dto.response.NotificationSelectResponse;
import kr.mywork.domain.post.service.dto.response.PostApprovalResponse;
import kr.mywork.domain.notification.service.dto.request.NotificationReadRequest;
import kr.mywork.interfaces.notification.controller.dto.request.NotificationReadWebRequest;
import kr.mywork.interfaces.notification.controller.dto.response.NotificationListSelectWebResponse;
import kr.mywork.interfaces.notification.controller.dto.response.NotificationReadWebResponse;
import kr.mywork.interfaces.notification.controller.dto.response.NotificationSelectWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.PostApprovalWebResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

	private final NotificationService notificationService;

	@GetMapping()
	public ApiResponse<NotificationListSelectWebResponse> getNotification(
		@LoginMember LoginMemberDetail loginMemberDetail,
		@RequestParam(name = "page") @Min(value = 1, message = "{invalid.page-size}") final int page,
		@RequestParam(name = "isRead", required = false) final Boolean isRead
	) {
		final List<NotificationSelectResponse> notificationSelectResponses = notificationService.findByConditionWithPaging(page,
			isRead, loginMemberDetail.memberId());

		final List<NotificationSelectWebResponse> notificationSelectWebResponses = notificationSelectResponses.stream()
			.map(NotificationSelectWebResponse::from)
			.toList();

		return ApiResponse.success(new NotificationListSelectWebResponse(notificationSelectWebResponses));
	}

	@PutMapping("")
	public ApiResponse<NotificationReadWebResponse> readNotification(
		@RequestBody @Valid NotificationReadWebRequest notificationReadWebRequest,
		@LoginMember LoginMemberDetail loginMemberDetail) {
		NotificationReadRequest notificationReadRequest = notificationReadWebRequest.toServiceDto();

		NotificationReadResponse notificationReadResponse = notificationService.readNotification(notificationReadRequest);

		return ApiResponse.success(new NotificationReadWebResponse(notificationReadResponse.getId()));
	}

	@GetMapping("/unread-count")
	public ApiResponse<Long> getUnreadNotificationCount(@LoginMember LoginMemberDetail loginMemberDetail) {
		long count = notificationService.countUnreadNotifications(loginMemberDetail.memberId());
		return ApiResponse.success(count);
	}
}
