package kr.mywork.interfaces.notification.controller.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NotificationListSelectWebResponse {

	List<NotificationSelectWebResponse> notificationSelectWebResponses;
}
