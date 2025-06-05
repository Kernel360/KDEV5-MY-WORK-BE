package kr.mywork.domain.project_checklist.repository;

import kr.mywork.domain.project_checklist.model.ProjectCheckList;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListCreateRequest;

public interface ProjectCheckListRepository {
	ProjectCheckList save(ProjectCheckListCreateRequest projectCheckListRequest);
}
