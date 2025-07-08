package kr.mywork.common.sse;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.Scheduled;
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

		if (sseEmitters.containsKey(clientId)) {
			return sseEmitters.get(clientId);
		} else {
			sseEmitters.put(clientId, sseEmitter);
		}

		sseEmitter.onCompletion(() -> {
			log.info("onCompletion sseEmitter : {}", sseEmitter);
			sseEmitters.remove(clientId);
		});
		sseEmitter.onTimeout(() -> {
			log.info("onTimeout sseEmitter : {}", sseEmitter);
			sseEmitters.remove(clientId);
		});
		sseEmitter.onError(e -> {
			log.info("onError sseEmitter : {}, error: {}", sseEmitter, e.getMessage());
			this.sseEmitters.remove(clientId);
		});

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
			return false;
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
			log.info("send sseEmitter error : {}", exception.getMessage());
			sseEmitters.remove(clientId);
			emitter.completeWithError(exception);
		}

		return false;
	}

	@Scheduled(fixedDelay = 30 * 1000L)
	public void ping() {
		for (Map.Entry<UUID, SseEmitter> emitterEntry : sseEmitters.entrySet()) {
			final SseEmitter emitter = emitterEntry.getValue();
			try {
				emitter.send(SseEmitter.event().name("ping").data("ping"));
			} catch (IOException e) {
				log.error("Error sending ping to emitter for clientId {}: {}", emitterEntry.getKey(), e.getMessage());
				sseEmitters.remove(emitterEntry.getKey());
				emitter.completeWithError(e);
			} catch (Exception e) {
				log.error("Unexpected error while sending ping: {}", e.getMessage());
				sseEmitters.remove(emitterEntry.getKey());
				emitter.completeWithError(e);
			}
		}
	}
}
