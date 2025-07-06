package kr.mywork.common.sse;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import kr.mywork.domain.notification.errors.NotificationEmitterNotFoundException;
import kr.mywork.domain.notification.errors.NotificationErrorType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SseEmitters {

	// key : memberId
	private final Map<UUID, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

	public SseEmitter add(UUID clientId) {
		final SseEmitter sseEmitter = new SseEmitter(600 * 1000L);

		sseEmitters.put(clientId, sseEmitter);

		sseEmitter.onCompletion(() -> sseEmitters.remove(clientId));
		sseEmitter.onTimeout(() -> sseEmitters.remove(clientId));
		sseEmitter.onError(e -> this.sseEmitters.remove(clientId));

		log.info("added sseEmitter id : {}, sseEmitters size: {}", clientId, sseEmitters.size());
		return sseEmitter;
	}

	public SseEmitter remove(UUID memberId) {
		if (!sseEmitters.containsKey(memberId)) {
			throw new NotificationEmitterNotFoundException(NotificationErrorType.EMITTER_NOT_FOUND);
		}

		log.debug("removing sseEmitter for memberId: {}", memberId);

		final SseEmitter removedEmitter = sseEmitters.remove(memberId);
		removedEmitter.complete();

		return removedEmitter;
	}

	public <T> boolean send(UUID clientId, String eventName, T data) {
		if (!sseEmitters.containsKey(clientId)) {
			throw new NotificationEmitterNotFoundException(NotificationErrorType.EMITTER_NOT_FOUND);
		}

		SseEmitter emitter = sseEmitters.get(clientId);

		try {
			emitter.send(
				SseEmitter.event()
					.name(eventName)
					.data(data)
					.build());

			return true;
		} catch (Exception exception) {
			sseEmitters.remove(clientId);
			emitter.completeWithError(exception);
		}

		return false;
	}
}
