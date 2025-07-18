package kr.mywork.interfaces.project_checklist.controller.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectCheckListCreateWebRequest {

	@NotBlank
	@Size(min = 1, max = 100)
	private String title;

	@NotBlank
	@Size(min = 1, max = 500)
	private String content;

	@NotNull
	private UUID projectStepId;

	@NotNull
	private String approval;

	public ProjectCheckListCreateRequest toServiceDto(LoginMemberDetail loginMemberDetail) {
		return new ProjectCheckListCreateRequest(
			loginMemberDetail.memberId(),
			loginMemberDetail.memberName(),
			loginMemberDetail.companyId(),
			loginMemberDetail.companyName(),
			this.title,
			this.content,
			this.projectStepId,
			this.approval);
	}
}
