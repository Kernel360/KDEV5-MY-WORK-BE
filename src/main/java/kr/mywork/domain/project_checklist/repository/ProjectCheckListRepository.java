package kr.mywork.domain.project_checklist.repository;

import java.util.Optional;
import java.util.UUID;

import kr.mywork.domain.project_checklist.model.ProjectCheckList;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListCreateRequest;

public interface ProjectCheckListRepository {
	ProjectCheckList save(ProjectCheckListCreateRequest projectCheckListRequest);

	Optional<ProjectCheckList> findById(UUID checkListId);
}
