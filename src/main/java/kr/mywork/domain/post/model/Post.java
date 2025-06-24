package kr.mywork.domain.post.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.mywork.domain.post.service.dto.request.PostUpdateRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {

	@Id
	private UUID id;

	private UUID projectStepId;

	@Column(length = 200)
	private String title;

	@Column(length = 30)
	private String companyName;

	@Column(length = 30)
	private String authorName;

	@Column(length = 500)
	private String content;

	@Column(length = 30)
	private String approval;

	private Boolean deleted;

	@UpdateTimestamp
	private LocalDateTime modifiedAt;

	@CreationTimestamp
	private LocalDateTime createdAt;

	public Post(final UUID id, final UUID projectStepId, final String title, final String companyName,
		final String authorName, final String content, String approval, Boolean deleted) {
		this.id = id;
		this.projectStepId = projectStepId;
		this.title = title;
		this.companyName = companyName;
		this.authorName = authorName;
		this.content = content;
		this.approval = approval;
		this.deleted = deleted;
	}

	public void update(PostUpdateRequest postUpdateRequest) {
		this.content = postUpdateRequest.getContent();
		this.title = postUpdateRequest.getTitle();
	}

	public void delete() {
		this.deleted = true;
	}

	public static Post copyOf(final Post post) {
		return new Post(
			post.id,
			post.projectStepId,
			post.title,
			post.companyName,
			post.authorName,
			post.content,
			post.approval,
			post.deleted);
	}

	public void changeApproval(String approval) {
		this.approval = approval;
	}
}
