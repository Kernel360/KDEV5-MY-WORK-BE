package kr.mywork.domain.post.service;

import java.net.URL;
import java.time.Duration;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.domain.post.errors.PostErrorType;
import kr.mywork.domain.post.errors.PostNotFoundException;
import kr.mywork.domain.post.model.Post;
import kr.mywork.domain.post.model.PostAttachment;
import kr.mywork.domain.post.repository.PostAttachmentRepository;
import kr.mywork.domain.post.repository.PostRepository;
import kr.mywork.domain.post.service.dto.response.PostAttachmentActiveResponse;
import kr.mywork.domain.post.service.dto.response.PostAttachmentDeleteResponse;
import kr.mywork.domain.post.service.dto.response.PostAttachmentDownloadResponse;
import kr.mywork.domain.post.service.dto.response.PostAttachmentUploadUrlIssueResponse;
import kr.mywork.domain.post.service.dto.response.PostAttachmentUploadUrlReissueResponse;
import kr.mywork.domain.post.service.errors.PostAttachmentAlreadyUploadException;
import kr.mywork.domain.post.service.errors.PostAttachmentInactiveException;
import kr.mywork.domain.post.service.errors.PostAttachmentNotFoundException;
import kr.mywork.domain.post.uploader.PostAttachmentFileHandler;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostAttachmentUploadService {

	private final PostRepository postRepository;
	private final PostAttachmentRepository postAttachmentRepository;
	private final PostAttachmentFileHandler postAttachmentFileHandler;

	@Transactional
	public PostAttachmentUploadUrlIssueResponse issuePostAttachmentUploadUrl(final UUID postId, final String fileName) {
		final Post savedPost = postRepository.findById(postId)
			.orElseThrow(() -> new PostNotFoundException(PostErrorType.POST_NOT_FOUND));

		final URL uploadUrl = postAttachmentFileHandler.createUploadUrl(postId, fileName);

		// TODO 업로드 파일 크기, 확장자, 첨부 파일 갯수 (3개) 검토 필요
		final PostAttachment postAttachment = PostAttachment.inactivePostAttachment(savedPost.getId(), fileName);
		postAttachmentRepository.save(postAttachment);

		return new PostAttachmentUploadUrlIssueResponse(postAttachment.getId(), uploadUrl.toString());
	}

	@Transactional
	public PostAttachmentUploadUrlReissueResponse reissuePostAttachmentUploadUrl(
		final UUID postAttachmentId, final String fileName) {

		final PostAttachment postAttachment = postAttachmentRepository.findById(postAttachmentId)
			.orElseThrow(() -> new PostAttachmentNotFoundException(PostErrorType.ATTACHMENT_NOT_FOUND));

		if (postAttachment.isActive()) {
			throw new PostAttachmentAlreadyUploadException(PostErrorType.ATTACHMENT_ALREADY_UPLOADED);
		}

		final URL uploadUrl = postAttachmentFileHandler.createUploadUrl(postAttachment.getId(), fileName);

		return new PostAttachmentUploadUrlReissueResponse(postAttachmentId, uploadUrl.toString());
	}

	@Transactional
	public PostAttachmentActiveResponse updatePostAttachmentActive(final UUID postAttachmentId, final Boolean active) {

		final PostAttachment postAttachment = postAttachmentRepository.findById(postAttachmentId)
			.orElseThrow(() -> new PostAttachmentNotFoundException(PostErrorType.ATTACHMENT_NOT_FOUND));

		postAttachment.updateActive(active);

		return new PostAttachmentActiveResponse(postAttachmentId, postAttachment.isActive());
	}

	@Transactional
	public PostAttachmentDownloadResponse issueDownloadUrl(final UUID postAttachmentId) {
		final PostAttachment postAttachment = postAttachmentRepository.findById(postAttachmentId)
			.orElseThrow(() -> new PostAttachmentNotFoundException(PostErrorType.ATTACHMENT_NOT_FOUND));

		if (postAttachment.isInactive()) {
			throw new PostAttachmentInactiveException(PostErrorType.ATTACHMENT_INACTIVE);
		}

		final URL downloadUrl = postAttachmentFileHandler.issueDownloadUrl(postAttachment.getFilePath(),
			Duration.ofMinutes(3));

		return new PostAttachmentDownloadResponse(postAttachment.getId(), downloadUrl.toString());
	}

	@Transactional
	public PostAttachmentDeleteResponse deletePostAttachment(final UUID postAttachmentId) {
		final PostAttachment postAttachment = postAttachmentRepository.findById(postAttachmentId)
			.orElseThrow(() -> new PostAttachmentNotFoundException(PostErrorType.ATTACHMENT_NOT_FOUND));

		postAttachmentFileHandler.delete(postAttachment.getFilePath());
		final boolean deleted = postAttachment.delete();

		return new PostAttachmentDeleteResponse(postAttachment.getId(), deleted);
	}
}
