package kr.mywork.domain.post.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostId {

	@Id
	@Getter
	private UUID id;

	@Column(nullable = false, columnDefinition = "timestamp")
	@CreationTimestamp
	@ColumnDefault("'1970-01-01 00:00:01'")
	private LocalDateTime createdAt;

	public PostId(final UUID id) {
		this.id = id;
	}
}
