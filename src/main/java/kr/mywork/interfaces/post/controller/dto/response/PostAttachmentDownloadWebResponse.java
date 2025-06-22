package kr.mywork.interfaces.post.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.post.service.dto.response.PostAttachmentDownloadResponse;

public record PostAttachmentDownloadWebResponse(UUID postAttachmentId, String downloadUrl) {

	public static PostAttachmentDownloadWebResponse fromServiceDto(
		final PostAttachmentDownloadResponse postAttachmentDownloadResponse) {
		return new PostAttachmentDownloadWebResponse(
			postAttachmentDownloadResponse.postAttachmentId(),
			postAttachmentDownloadResponse.downloadUrl());
	}
}
