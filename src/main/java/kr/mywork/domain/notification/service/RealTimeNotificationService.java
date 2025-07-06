package kr.mywork.domain.notification.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import kr.mywork.common.sse.SseEmitters;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RealTimeNotificationService {

	private final SseEmitters sseEmitters;

	public SseEmitter addSseEmitter(final UUID clientId) {
		return sseEmitters.add(clientId);
	}

	public SseEmitter removeSseEmitter(final UUID clientId) {
		return sseEmitters.remove(clientId);
	}

	public <T> boolean sendNotification(final UUID clientId, final String eventName, final T data) {
		return sseEmitters.send(clientId, eventName, data);
	}

}
