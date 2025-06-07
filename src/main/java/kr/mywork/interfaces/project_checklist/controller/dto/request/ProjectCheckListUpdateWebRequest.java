package kr.mywork.interfaces.project_checklist.controller.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectCheckListUpdateWebRequest {

	@NotNull
	private UUID id;

	@NotBlank
	@Size(min = 1, max = 100)
	private String title;

	public ProjectCheckListUpdateRequest toServiceDto() {
		return new ProjectCheckListUpdateRequest(this.id, this.title);
	}
}
