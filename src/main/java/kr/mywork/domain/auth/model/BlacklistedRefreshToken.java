package kr.mywork.domain.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.mywork.common.rdb.id.UnixTimeOrderedUuidGeneratedValue;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlacklistedRefreshToken {

	@Id
	@UnixTimeOrderedUuidGeneratedValue
	private UUID id;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String token;

	@Column(nullable = false)
	private LocalDateTime expiresAt;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@Builder
	public BlacklistedRefreshToken(String token, LocalDateTime expiresAt) {
		this.token = token;
		this.expiresAt = expiresAt;
	}
}
