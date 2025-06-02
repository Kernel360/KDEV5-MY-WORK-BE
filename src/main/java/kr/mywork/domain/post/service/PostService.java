package kr.mywork.domain.post.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.uuid.Generators;

import jakarta.transaction.Transactional;
import kr.mywork.domain.post.errors.PostErrorType;
import kr.mywork.domain.post.errors.PostIdNotFoundException;
import kr.mywork.domain.post.model.Post;
import kr.mywork.domain.post.repository.PostIdRepository;
import kr.mywork.domain.post.repository.PostRepository;
import kr.mywork.domain.post.service.dto.request.PostCreateRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PostService {

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
}
