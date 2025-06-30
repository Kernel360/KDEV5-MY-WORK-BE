package kr.mywork.domain.post.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.uuid.Generators;

import kr.mywork.domain.post.repository.PostIdRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostIdService {

	private final PostIdRepository postIdRepository;

	@Transactional
	public UUID createPostId() {
		final UUID postId = Generators.timeBasedEpochGenerator().generate();
		return postIdRepository.save(postId);
	}
}
