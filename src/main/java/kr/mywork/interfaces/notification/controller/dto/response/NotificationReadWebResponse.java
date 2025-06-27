package kr.mywork.interfaces.notification.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.notification.service.dto.response.NotificationReadResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotificationReadWebResponse {
	private UUID id;

	public NotificationReadResponse toServiceDto() {
		return new NotificationReadResponse(this.id);
	}
}
