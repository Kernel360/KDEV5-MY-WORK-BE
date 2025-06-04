package kr.mywork.interfaces.post.controller.dto.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class ReviewModifyWebRequest {

	@Length(min = 1, max = 200, message = "{review.invalid-comment}")
	@NotBlank(message = "{review.blank-comment}")
	private final String comment;

}
