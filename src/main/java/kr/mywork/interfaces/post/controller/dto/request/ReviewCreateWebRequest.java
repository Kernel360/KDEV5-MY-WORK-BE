package kr.mywork.interfaces.post.controller.dto.request;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class ReviewCreateWebRequest {
	private final UUID postId;

	@Length(min = 1, max = 200, message = "{review.invalid-comment}")
	private final String comment;

	private final UUID parentId;
}
