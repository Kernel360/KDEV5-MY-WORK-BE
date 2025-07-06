package kr.mywork.domain.post.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEventPublisher;

import kr.mywork.domain.post.repository.PostRepository;
import kr.mywork.domain.post.repository.ReviewRepository;
import kr.mywork.domain.post.service.dto.ReviewSelectResponse;
import kr.mywork.domain.project_step.repository.ProjectStepRepository;

class ReviewServiceTest {

	private ReviewService reviewService;
	private ReviewRepository reviewRepository;
	private PostRepository postRepository;
	private ProjectStepRepository projectStepRepository;
	private ApplicationEventPublisher eventPublisher;

	@BeforeEach
	void setUp() {
		this.reviewRepository = Mockito.mock(ReviewRepository.class);
		this.postRepository = Mockito.mock(PostRepository.class);
		this.projectStepRepository = Mockito.mock(ProjectStepRepository.class);
		this.eventPublisher = Mockito.mock(ApplicationEventPublisher.class);
		this.reviewService = new ReviewService(reviewRepository, postRepository, projectStepRepository, eventPublisher);
	}

	@Test
	@DisplayName("리뷰_목록_조회_서비스_테스트")
	void 리뷰_목록_조회_서비스_테스트() {
		// given
		final UUID postId = UUID.fromString("019748f3-9555-7056-8590-70dc155cc6fa");
		List<ReviewSelectResponse> parentReviewSelectResponses = initParentReviewSelectResponses();
		final List<ReviewSelectResponse> childReviewSelectResponses = getChildReviewSelectResponses();

		when(reviewRepository.findParentReviewsWithPaging(any(), anyInt(), anyInt())).thenReturn(
			parentReviewSelectResponses);

		when(reviewRepository.findByIds(any(), any())).thenReturn(childReviewSelectResponses);

		// when
		final List<ReviewSelectResponse> reviewSelectResponses = reviewService.findAllReviewsWithPaging(postId, 1);

		// then
		System.out.println("reviewSelectResponses = " + reviewSelectResponses);
	}

	private @NotNull List<ReviewSelectResponse> initParentReviewSelectResponses() {
		return List.of(
			new ReviewSelectResponse(UUID.fromString("019748f2-244f-702d-aa8c-a7b27d255c2e"), "홍길동", "매우 유익한 리뷰입니다 0",
				"Company01", null, new ArrayList<>(), LocalDateTime.of(2025, 6, 8, 10, 0)),
			new ReviewSelectResponse(UUID.fromString("01974900-0d38-71d6-8140-66826a6172f5"), "홍길동", "매우 유익한 리뷰입니다 1",
				"Company01", null, new ArrayList<>(), LocalDateTime.of(2025, 6, 8, 9, 0)),
			new ReviewSelectResponse(UUID.fromString("019748f2-244f-702e-bbcc-92aa1d223bcd"), "김철수", "매우 유익한 리뷰입니다 2",
				"Company02", null, new ArrayList<>(), LocalDateTime.of(2025, 6, 8, 8, 0)),
			new ReviewSelectResponse(UUID.fromString("019748f2-244f-702f-ddee-11aa1a6b91de"), "이영희", "매우 유익한 리뷰입니다 3",
				"Company01", null, new ArrayList<>(), LocalDateTime.of(2025, 6, 8, 7, 0)),
			new ReviewSelectResponse(UUID.fromString("019748f2-244f-7030-ffff-abcdef123456"), "박지민", "매우 유익한 리뷰입니다 4",
				"Company02", null, new ArrayList<>(), LocalDateTime.of(2025, 6, 8, 6, 0)),
			new ReviewSelectResponse(UUID.fromString("019748f2-244f-7031-a1a1-001122334455"), "최수연", "매우 유익한 리뷰입니다 5",
				"Company01", null, new ArrayList<>(), LocalDateTime.of(2025, 6, 8, 5, 0)),
			new ReviewSelectResponse(UUID.fromString("019748f2-244f-7032-c3c3-223344556677"), "정우성", "매우 유익한 리뷰입니다 6",
				"Company02", null, new ArrayList<>(), LocalDateTime.of(2025, 6, 8, 4, 0)),
			new ReviewSelectResponse(UUID.fromString("019748f2-244f-7033-e5e5-445566778899"), "한지민", "매우 유익한 리뷰입니다 7",
				"Company01", null, new ArrayList<>(), LocalDateTime.of(2025, 6, 8, 3, 0)),
			new ReviewSelectResponse(UUID.fromString("019748f2-244f-7034-a7a7-667788990011"), "서강준", "매우 유익한 리뷰입니다 8",
				"Company02", null, new ArrayList<>(), LocalDateTime.of(2025, 6, 8, 2, 0)),
			new ReviewSelectResponse(UUID.fromString("019748f2-244f-7035-c9c9-889900112233"), "배수지", "매우 유익한 리뷰입니다 9",
				"Company01", null, new ArrayList<>(), LocalDateTime.of(2025, 6, 8, 1, 0))
		);
	}

	private List<ReviewSelectResponse> getChildReviewSelectResponses() {
		return List.of(
			new ReviewSelectResponse(UUID.fromString("019748f3-3489-735b-bae8-e0d9521996b8"), "김철수", "대댓글 01",
				"Company02", UUID.fromString("019748f2-244f-702d-aa8c-a7b27d255c2e"), new ArrayList<>(),
				LocalDateTime.of(2025, 6, 8, 9, 0)),
			new ReviewSelectResponse(UUID.fromString("019748f5-67f9-73a3-bc3d-015e8b9aae63"), "김갑수", "대댓글 02",
				"Company02", UUID.fromString("019748f2-244f-702d-aa8c-a7b27d255c2e"), new ArrayList<>(),
				LocalDateTime.of(2025, 6, 8, 8, 0))
		);
	}

}
