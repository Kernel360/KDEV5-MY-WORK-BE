package kr.mywork.domain.post.service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostSelectResponse {
	private UUID postId;
	private LocalDateTime createAt;
	private String authorName;
	private String title;
	private String approval;
	private String projectStepTitle;

	public void assignProjectStepName(final String projectStepTitle) {
		this.projectStepTitle = projectStepTitle;
	}

	public PostSelectResponse(UUID postId, LocalDateTime createAt, String authorName, String title, String approval) {
		this.postId = postId;
		this.createAt = createAt;
		this.authorName = authorName;
		this.title = title;
		this.approval = approval;
	}
}
