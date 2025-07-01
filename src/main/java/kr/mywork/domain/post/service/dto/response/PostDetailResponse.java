package kr.mywork.domain.post.service.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import kr.mywork.domain.post.model.Post;
import kr.mywork.domain.post.model.PostAttachment;

public record PostDetailResponse(UUID postId, String title, String content, String companyName, String authorName,
								 String approval, LocalDateTime createdAt,
								 List<PostAttachmentDetailResponse> postAttachmentDetailResponses) {

	public static PostDetailResponse from(Post post, final List<PostAttachment> postAttachments) {
		return new PostDetailResponse(
			post.getId(),
			post.getTitle(),
			post.getContent(),
			post.getCompanyName(),
			post.getAuthorName(),
			post.getApproval(),
			post.getCreatedAt(),
			transformPostAttachmentDetailResponse(postAttachments)
		);
	}

	private static List<PostAttachmentDetailResponse> transformPostAttachmentDetailResponse(
		final List<PostAttachment> postAttachments) {
		return postAttachments.stream()
			.map(postAttachment -> new PostAttachmentDetailResponse(
				postAttachment.getId(),
				postAttachment.getFileName()))
			.collect(Collectors.toList());
	}
};
