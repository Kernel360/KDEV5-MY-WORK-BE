package kr.mywork.interfaces.notification.controller.dto.request;

import java.util.UUID;

import kr.mywork.domain.notification.service.dto.request.NotificationReadRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotificationReadWebRequest {
	private final UUID id;

	public NotificationReadRequest toServiceDto() {
		return new NotificationReadRequest(this.id);
	}
}
