package kr.mywork.domain.post.service;

import java.net.URL;
import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.domain.post.errors.MaxPostAttachmentsException;
import kr.mywork.domain.post.errors.PostErrorType;
import kr.mywork.domain.post.errors.PostIdNotFoundException;
import kr.mywork.domain.post.model.PostAttachment;
import kr.mywork.domain.post.model.PostId;
import kr.mywork.domain.post.repository.PostAttachmentRepository;
import kr.mywork.domain.post.repository.PostIdRepository;
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

	@Value("${post.attachment.max-count}")
	private int postAttachmentMaxCount;

	private final PostRepository postRepository;
	private final PostIdRepository postIdRepository;
	private final PostAttachmentRepository postAttachmentRepository;
	private final PostAttachmentFileHandler postAttachmentFileHandler;

	@Transactional
	public PostAttachmentUploadUrlIssueResponse issuePostAttachmentUploadUrl(final UUID postId, final String fileName) {
		return postRepository.findById(postId)
			.map(post -> createPostAttachmentToExistingPost(postId, fileName))
			.orElseGet(() -> createPostAttachmentToNewPost(postId, fileName));
	}

	private PostAttachmentUploadUrlIssueResponse createPostAttachmentToExistingPost(
		final UUID postId, final String fileName) {

		if(isAlreadyFileUploadName(postId, fileName)) {
			throw new PostAttachmentAlreadyUploadException(PostErrorType.ATTACHMENT_ALREADY_UPLOADED_NAME);
		}

		if (isMaxPostAttachmentCount(postId)) {
			throw new MaxPostAttachmentsException(PostErrorType.ATTACHMENT_MAX);
		}

		final PostAttachment postAttachment = PostAttachment.inactivePostAttachment(postId, fileName);
		final PostAttachment savedPostAttachment = postAttachmentRepository.save(postAttachment);

		final URL uploadUrl = postAttachmentFileHandler.createUploadUrl(postId, fileName);

		return new PostAttachmentUploadUrlIssueResponse(savedPostAttachment.getId(), uploadUrl.toString());
	}

	private PostAttachmentUploadUrlIssueResponse createPostAttachmentToNewPost(
		final UUID postId, final String fileName) {

		if(isAlreadyFileUploadName(postId, fileName)) {
			throw new PostAttachmentAlreadyUploadException(PostErrorType.ATTACHMENT_ALREADY_UPLOADED);
		}

		final PostId issuedPostId = postIdRepository.findById(postId)
			.orElseThrow(() -> new PostIdNotFoundException(PostErrorType.ID_NOT_FOUND));

		if (isMaxPostAttachmentCount(postId)) {
			throw new MaxPostAttachmentsException(PostErrorType.ATTACHMENT_MAX);
		}

		final PostAttachment postAttachment = PostAttachment.inactivePostAttachment(issuedPostId.getId(), fileName);
		final PostAttachment savedPostAttachment = postAttachmentRepository.save(postAttachment);

		final URL uploadUrl = postAttachmentFileHandler.createUploadUrl(postId, fileName);

		return new PostAttachmentUploadUrlIssueResponse(savedPostAttachment.getId(), uploadUrl.toString());
	}

	private boolean isAlreadyFileUploadName(final UUID postId, final String fileName) {
		return postAttachmentRepository.existsByFileNameAndDeleted(postId, fileName, false);
	}

	private boolean isMaxPostAttachmentCount(final UUID postId) {
		return postAttachmentRepository.countByDeletedAndActive(postId, false, true) >= postAttachmentMaxCount;
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
