package kr.mywork.domain.notification.service.dto.request;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NotificationReadRequest {
	private UUID id;
}
