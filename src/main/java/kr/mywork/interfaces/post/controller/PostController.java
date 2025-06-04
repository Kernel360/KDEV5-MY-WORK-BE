package kr.mywork.interfaces.post.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.post.service.PostService;
import kr.mywork.domain.post.service.dto.request.PostCreateRequest;
import kr.mywork.domain.post.service.dto.request.PostUpdateRequest;
import kr.mywork.domain.post.service.dto.response.PostDetailResponse;
import kr.mywork.domain.post.service.dto.response.PostUpdateResponse;
import kr.mywork.interfaces.post.controller.dto.request.PostCreateWebRequest;
import kr.mywork.interfaces.post.controller.dto.request.PostUpdateWebRequest;
import kr.mywork.interfaces.post.controller.dto.response.PostCreateWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.PostDetailWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.PostIdCreateWebResponse;
import kr.mywork.interfaces.post.controller.dto.response.PostUpdateWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping("/id/generate")
	public ApiResponse<PostIdCreateWebResponse> createPostId() {
		final UUID postId = postService.createPostId();
		return ApiResponse.success(new PostIdCreateWebResponse(postId));
	}

	@PostMapping
	public ApiResponse<PostCreateWebResponse> createPost(
		@RequestBody @Valid final PostCreateWebRequest postCreateWebRequest) {

		final PostCreateRequest postCreateRequest = postCreateWebRequest.toServiceDto();

		final UUID createdPostId = postService.createPost(postCreateRequest);

		final PostCreateWebResponse postCreateWebResponse = new PostCreateWebResponse(createdPostId);

		return ApiResponse.success(postCreateWebResponse);

	}

	@PutMapping("/{postId}")
	public ApiResponse<PostUpdateWebResponse> updatePost(
		@RequestBody @Valid final PostUpdateWebRequest postUpdateWebRequest,
		@PathVariable final UUID postId) {

		final PostUpdateRequest postUpdateRequest = postUpdateWebRequest.toServiceDto(postId);

		final PostUpdateResponse postUpdateResponse = postService.updatePost(postUpdateRequest);

		final PostUpdateWebResponse postUpdateWebResponse = PostUpdateWebResponse.from(postUpdateResponse);

		return ApiResponse.success(postUpdateWebResponse);
	}

	@GetMapping("/{postId}")
	public ApiResponse<PostDetailWebResponse> getPostDetail(@PathVariable @Valid final UUID postId) {

		PostDetailResponse postDetailResponse = postService.getPostDetail(postId);

		PostDetailWebResponse postDetailWebResponse = PostDetailWebResponse.from(postDetailResponse);

		return ApiResponse.success(postDetailWebResponse);
	}

}
