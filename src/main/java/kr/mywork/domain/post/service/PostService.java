package kr.mywork.domain.post.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.uuid.Generators;

import jakarta.transaction.Transactional;
import kr.mywork.domain.post.errors.PostErrorType;
import kr.mywork.domain.post.errors.PostIdNotFoundException;
import kr.mywork.domain.post.errors.PostNotFoundException;
import kr.mywork.domain.post.model.Post;
import kr.mywork.domain.post.repository.PostIdRepository;
import kr.mywork.domain.post.repository.PostRepository;
import kr.mywork.domain.post.service.dto.request.PostCreateRequest;
import kr.mywork.domain.post.service.dto.request.PostUpdateRequest;
import kr.mywork.domain.post.service.dto.response.PostDetailResponse;
import kr.mywork.domain.post.service.dto.response.PostSelectResponse;
import kr.mywork.domain.post.service.dto.response.PostUpdateResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	@Value("${post.page.size}")
	private int postPageSize;

	private final PostRepository postRepository;
	private final PostIdRepository postIdRepository;

	@Transactional
	public UUID createPostId() {
		final UUID postId = Generators.timeBasedEpochGenerator().generate();
		return postIdRepository.save(postId);
	}

	@Transactional
	public UUID createPost(PostCreateRequest postCreateRequest) {
		postIdRepository.findById(postCreateRequest.getId())
			.orElseThrow(() -> new PostIdNotFoundException(PostErrorType.ID_NOT_FOUND));

		final Post savedPost = postRepository.save(postCreateRequest);
		return savedPost.getId();
	}

	@Transactional
	public PostUpdateResponse updatePost(PostUpdateRequest postUpdateRequest) {
		Post post = postRepository.findById(postUpdateRequest.getId())
			.orElseThrow(() -> new PostNotFoundException(PostErrorType.POST_NOT_FOUND));

		post.update(postUpdateRequest);
		return PostUpdateResponse.from(post);
	}

	@Transactional
	public PostDetailResponse getPostDetail(UUID postId) {
		final Post post = postRepository.findById(postId)
			.orElseThrow(() -> new PostNotFoundException(PostErrorType.POST_NOT_FOUND));

		return PostDetailResponse.from(post);
	}

	@Transactional
	public List<PostSelectResponse> findPostsBySearchConditionWithPaging(final int page, final UUID projectStepId,
		final String keyword, final Boolean deleted) {

		// TODO projectStepId가 null이 아니고, DB에도 존재 하지 않을때 에러 처리해아함.

		return postRepository.findPostsBySearchConditionWithPaging(page, postPageSize, projectStepId, keyword, deleted);
	}

	public Long countTotalPostsByCondition(UUID projectStepId, String keyword, Boolean deleted) {
		return postRepository.countTotalPostsByCondition(projectStepId, keyword, deleted);
	}
}
