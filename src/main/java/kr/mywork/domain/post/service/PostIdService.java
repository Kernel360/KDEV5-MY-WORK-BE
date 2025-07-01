package kr.mywork.domain.post.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.uuid.Generators;

import kr.mywork.domain.post.repository.PostIdRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostIdService {

	@Value("${post.issued-id.created-limit-hour}")
	private int createdLimitHour;

	private final PostIdRepository postIdRepository;

	@Transactional
	public UUID createPostId() {
		final UUID postId = Generators.timeBasedEpochGenerator().generate();
		return postIdRepository.save(postId);
	}

	@Transactional
	public Long deleteIssuedPostIds(final LocalDateTime nowDateTime) {
		return postIdRepository.deleteIssuedPostIdsLessOrEqualLimitTime(nowDateTime.minusHours(createdLimitHour));
	}
}
