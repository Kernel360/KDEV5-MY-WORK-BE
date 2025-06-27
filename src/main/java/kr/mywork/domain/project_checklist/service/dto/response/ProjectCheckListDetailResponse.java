package kr.mywork.domain.project_checklist.service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import kr.mywork.domain.project_checklist.model.ProjectCheckList;

public record ProjectCheckListDetailResponse(UUID id, String title, String content, String authorName, String companyName, String approval, LocalDateTime createdAt) {

	public ProjectCheckListDetailResponse(ProjectCheckList projectCheckList) {
		this(
			projectCheckList.getId(),
			projectCheckList.getTitle(),
			projectCheckList.getContent(),
			projectCheckList.getAuthorName(),
			projectCheckList.getCompanyName(),
			projectCheckList.getApproval(),
			projectCheckList.getCreatedAt()
		);
	}

}
