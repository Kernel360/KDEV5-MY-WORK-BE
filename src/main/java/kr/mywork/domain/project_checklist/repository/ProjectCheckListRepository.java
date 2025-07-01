package kr.mywork.domain.project_checklist.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import kr.mywork.domain.project_checklist.model.ProjectCheckList;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListCreateRequest;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListSelectResponse;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectStepCheckListCountResponse;

public interface ProjectCheckListRepository {
	ProjectCheckList save(ProjectCheckListCreateRequest projectCheckListRequest);

	Optional<ProjectCheckList> findById(UUID checkListId);

	List<ProjectStepCheckListCountResponse> findProgressCountGroupByProjectStepIdAndApproval(
		Collection<UUID> projectStepIds, String approval);

	List<ProjectCheckListSelectResponse> findAllByProjectIdAndStepId(UUID projectId, UUID projectStepId);
}
