package kr.mywork.domain.post.model;

import java.util.UUID;

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

	public PostId(final UUID id) {
		this.id = id;
	}
}
