package kr.mywork.interfaces.post.controller;

import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.post.service.PostIdService;
import kr.mywork.interfaces.post.controller.dto.response.PostIdCreateWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class PostIdController {

	private final PostIdService postIdService;

	@PostMapping("/posts/id/generate")
	public ApiResponse<PostIdCreateWebResponse> createPostId() {
		final UUID postId = postIdService.createPostId();
		return ApiResponse.success(new PostIdCreateWebResponse(postId));
	}
}
