package kr.mywork.interfaces.post.controller.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import kr.mywork.domain.post.service.dto.response.PostAttachmentDetailResponse;
import kr.mywork.domain.post.service.dto.response.PostDetailResponse;

public record PostDetailWebResponse(UUID postId, String title, String content, String companyName, String authorName,
									String approval, @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime createdAt,
									@JsonProperty("postAttachments") List<PostAttachmentDetailWebResponse> postAttachmentDetailWebResponses) {

	public static PostDetailWebResponse from(PostDetailResponse postDetailResponse) {
		return new PostDetailWebResponse(
			postDetailResponse.postId(),
			postDetailResponse.title(),
			postDetailResponse.content(),
			postDetailResponse.companyName(),
			postDetailResponse.authorName(),
			postDetailResponse.approval(),
			postDetailResponse.createdAt(),
			transformWebResponse(postDetailResponse.postAttachmentDetailResponses())
		);
	}

	private static List<PostAttachmentDetailWebResponse> transformWebResponse(
		final List<PostAttachmentDetailResponse> postAttachmentDetailResponses) {
		return postAttachmentDetailResponses.stream()
			.map(PostAttachmentDetailWebResponse::fromServiceDto)
			.collect(Collectors.toList());
	}
}
