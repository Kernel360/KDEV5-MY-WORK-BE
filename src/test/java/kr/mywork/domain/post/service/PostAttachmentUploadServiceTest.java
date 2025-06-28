package kr.mywork.domain.post.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kr.mywork.domain.post.errors.MaxPostAttachmentsException;
import kr.mywork.domain.post.model.Post;
import kr.mywork.domain.post.model.PostAttachment;
import kr.mywork.domain.post.model.PostId;
import kr.mywork.domain.post.repository.PostAttachmentRepository;
import kr.mywork.domain.post.repository.PostIdRepository;
import kr.mywork.domain.post.repository.PostRepository;
import kr.mywork.domain.post.service.dto.response.PostAttachmentUploadUrlIssueResponse;
import kr.mywork.domain.post.service.errors.PostAttachmentAlreadyUploadException;
import kr.mywork.domain.post.uploader.PostAttachmentFileHandler;

class PostAttachmentUploadServiceTest {

	private PostAttachmentUploadService postAttachmentUploadService;

	private PostRepository postRepository;
	private PostIdRepository postIdRepository;
	private PostAttachmentRepository postAttachmentRepository;
	private PostAttachmentFileHandler postAttachmentFileHandler;

	@BeforeEach
	public void setUp() {
		this.postRepository = Mockito.mock(PostRepository.class);
		this.postIdRepository = Mockito.mock(PostIdRepository.class);
		this.postAttachmentRepository = Mockito.mock(PostAttachmentRepository.class);
		this.postAttachmentFileHandler = Mockito.mock(PostAttachmentFileHandler.class);

		this.postAttachmentUploadService = new PostAttachmentUploadService(
			postRepository, postIdRepository, postAttachmentRepository, postAttachmentFileHandler);

		initEnvironmentVariable();
	}

	private void initEnvironmentVariable() {
		try {
			Field field = PostAttachmentUploadService.class.getDeclaredField("postAttachmentMaxCount");
			field.setAccessible(true);
			field.set(postAttachmentUploadService, 3);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("게시글 생성 이전 파일 첨부 성공")
	void 게시글_생성_이전_파일_첨부_성공() throws MalformedURLException {
		// given
		final UUID postId = UUID.fromString("0197b69f-e45a-74ec-937e-d236bb1cdddf");
		final String fileName = "file.png";
		final URL uploadUrl = new URL("http://localhost:8080/upload-url/file.png");

		when(postRepository.findById(any())).thenReturn(Optional.empty());
		when(postAttachmentRepository.existsByFileNameAndDeleted(any(), any(), anyBoolean())).thenReturn(false);
		when(postAttachmentRepository.countByDeletedAndActive(any(), anyBoolean(), anyBoolean())).thenReturn(0L);
		when(postAttachmentRepository.save(any())).thenReturn(PostAttachment.inactivePostAttachment(postId, fileName));
		when(postIdRepository.findById(any())).thenReturn(Optional.of(new PostId(postId)));
		when(postAttachmentFileHandler.createUploadUrl(any(), any())).thenReturn(uploadUrl);

		// when
		final PostAttachmentUploadUrlIssueResponse postAttachmentUploadUrlIssueResponse =
			postAttachmentUploadService.issuePostAttachmentUploadUrl(postId, fileName);

		// then
		assertThat(postAttachmentUploadUrlIssueResponse.uploadUrl()).isEqualTo(uploadUrl.toString());
	}

	@Test
	@DisplayName("게시글 등록 이후 파일 첨부 성공")
	void 새로운_게시글을_등록하면서_파일_첨부_성공() throws MalformedURLException {
		// given
		final UUID postId = UUID.fromString("0197b69f-e45a-74ec-937e-d236bb1cdddf");
		final String fileName = "file.png";
		final URL uploadUrl = new URL("http://localhost:8080/upload-url/file.png");

		final Post post = new Post(postId, UUID.fromString("0197b6ab-70f5-79b5-8826-d19fe814fce5"), "제목", "회사명", "작성자명",
			UUID.fromString("0197b6ab-f2ca-7ef9-aecd-819a678ca1c2"), "내용물", "PENDING", false);

		when(postRepository.findById(any())).thenReturn(Optional.of(post));
		when(postAttachmentRepository.existsByFileNameAndDeleted(any(), any(), anyBoolean())).thenReturn(false);
		when(postAttachmentRepository.countByDeletedAndActive(any(), anyBoolean(), anyBoolean())).thenReturn(2L);
		when(postAttachmentRepository.save(any())).thenReturn(PostAttachment.inactivePostAttachment(postId, fileName));
		when(postAttachmentFileHandler.createUploadUrl(any(), any())).thenReturn(uploadUrl);

		// when
		final PostAttachmentUploadUrlIssueResponse postAttachmentUploadUrlIssueResponse =
			postAttachmentUploadService.issuePostAttachmentUploadUrl(postId, fileName);

		// then
		assertThat(postAttachmentUploadUrlIssueResponse.uploadUrl()).isEqualTo(uploadUrl.toString());
	}

	@Test
	@DisplayName("게시글 등록 이전 첨부 파일 3건 이상이면 파일 첨부 불가")
	void 게시글_등록_이전_첨부_파일_3건_이상이면_파일_첨부_불가() {
		// given
		final UUID postId = UUID.fromString("0197b69f-e45a-74ec-937e-d236bb1cdddf");
		final String fileName = "file.png";

		when(postRepository.findById(any())).thenReturn(Optional.empty());
		when(postIdRepository.findById(any())).thenReturn(Optional.of(new PostId(postId)));
		when(postAttachmentRepository.countByDeletedAndActive(any(), anyBoolean(), anyBoolean())).thenReturn(3L);

		// when, then
		assertThatThrownBy(
			() -> postAttachmentUploadService.issuePostAttachmentUploadUrl(postId, fileName))
			.isInstanceOf(MaxPostAttachmentsException.class);
	}

	@Test
	@DisplayName("게시글 등록 이후 첨부 파일 3건 이상이면 파일 첨부 불가")
	void 게시글_등록_이후_첨부_파일_3건_이상이면_파일_첨부_불가() {
		// given
		final UUID postId = UUID.fromString("0197b69f-e45a-74ec-937e-d236bb1cdddf");
		final String fileName = "file.png";

		final Post post = new Post(postId, UUID.fromString("0197b6ab-70f5-79b5-8826-d19fe814fce5"), "제목", "회사명", "작성자명",
			UUID.fromString("0197b6ab-f2ca-7ef9-aecd-819a678ca1c2"), "내용물", "PENDING", false);

		when(postRepository.findById(any())).thenReturn(Optional.of(post));
		when(postAttachmentRepository.countByDeletedAndActive(any(), anyBoolean(), anyBoolean())).thenReturn(3L);

		// when, then
		assertThatThrownBy(
			() -> postAttachmentUploadService.issuePostAttachmentUploadUrl(postId, fileName))
			.isInstanceOf(MaxPostAttachmentsException.class);
	}

	@Test
	@DisplayName("게시글 등록 이전 기존 첨부 파일 이름이 존재하는 경우 첨부 불가")
	void 게시글_등록_이전_기존_첨부_파일_이름이_존재하는_경우_첨부_불가() {
		// given
		final UUID postId = UUID.fromString("0197b69f-e45a-74ec-937e-d236bb1cdddf");
		final String fileName = "file.png";

		when(postRepository.findById(any())).thenReturn(Optional.empty());
		when(postIdRepository.findById(any())).thenReturn(Optional.of(new PostId(postId)));
		when(postAttachmentRepository.existsByFileNameAndDeleted(any(), any(), anyBoolean())).thenReturn(true);

		// when, then
		assertThatThrownBy(
			() -> postAttachmentUploadService.issuePostAttachmentUploadUrl(postId, fileName))
			.isInstanceOf(PostAttachmentAlreadyUploadException.class);
	}

	@Test
	@DisplayName("게시글 등록 이후 기존 첨부 파일 이름이 존재하는 경우 첨부 불가")
	void 게시글_등록_이후_기존_첨부_파일_이름이_존재하는_경우_첨부_불가() throws MalformedURLException {
		// given
		// given
		final UUID postId = UUID.fromString("0197b69f-e45a-74ec-937e-d236bb1cdddf");
		final String fileName = "file.png";
		final URL uploadUrl = new URL("http://localhost:8080/upload-url/file.png");

		final Post post = new Post(postId, UUID.fromString("0197b6ab-70f5-79b5-8826-d19fe814fce5"), "제목", "회사명", "작성자명",
			UUID.fromString("0197b6ab-f2ca-7ef9-aecd-819a678ca1c2"), "내용물", "PENDING", false);

		when(postRepository.findById(any())).thenReturn(Optional.of(post));
		when(postAttachmentRepository.existsByFileNameAndDeleted(any(), any(), anyBoolean())).thenReturn(true);

		// when, then
		assertThatThrownBy(
			() -> postAttachmentUploadService.issuePostAttachmentUploadUrl(postId, fileName))
			.isInstanceOf(PostAttachmentAlreadyUploadException.class);
	}
}
