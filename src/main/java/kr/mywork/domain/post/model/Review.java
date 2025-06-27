package kr.mywork.domain.post.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.mywork.common.rdb.id.UnixTimeOrderedUuidGeneratedValue;
import kr.mywork.domain.post.errors.review.ReviewAlreadyDeletedException;
import kr.mywork.domain.post.errors.review.ReviewErrorType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

	@Id
	@UnixTimeOrderedUuidGeneratedValue
	private UUID id;

	@Column(nullable = false)
	private UUID postId;

	@Column
	private UUID parentId;

	@Column(nullable = false)
	private UUID memberId; // 작성자 검증을 위한 필드

	@Column(nullable = false, length = 200)
	@Getter
	private String comment;

	@Column(nullable = false, length = 30)
	private String companyName;

	private String authorName;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Boolean deleted = false;

	@Column(nullable = false, columnDefinition = "timestamp")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(nullable = false, columnDefinition = "timestamp")
	@UpdateTimestamp
	private LocalDateTime modifiedAt;

	public Review(final UUID postId, final UUID parentId, final UUID memberId, final String comment,
		final String companyName, final String authorName) {
		this.postId = postId;
		this.parentId = parentId;
		this.memberId = memberId;
		this.comment = comment;
		this.companyName = companyName;
		this.authorName = authorName;
		this.deleted = false;
	}

	public static Review copyOf(Review review) {
		return new Review(
			review.postId,
			review.parentId,
			review.memberId,
			review.comment,
			review.companyName,
			review.authorName);
	}

	public boolean modifyComment(final String comment) {
		this.comment = comment;
		return true;
	}

	public boolean delete() {
		if (this.deleted) {
			throw new ReviewAlreadyDeletedException(ReviewErrorType.REVIEW_ALREADY_DELETED);
		}

		this.deleted = true;
		return true;
	}
}
