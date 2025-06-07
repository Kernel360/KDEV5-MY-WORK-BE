package kr.mywork.infrastructure.project_checklist.rdb;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import kr.mywork.domain.project_checklist.model.ProjectCheckList;
import kr.mywork.domain.project_checklist.repository.ProjectCheckListRepository;
import kr.mywork.domain.project_checklist.service.dto.request.ProjectCheckListCreateRequest;
import kr.mywork.domain.project_checklist.service.dto.response.ProjectCheckListDetailResponse;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryDslProjectCheckListRepository implements ProjectCheckListRepository {

	private final JpaProjectCheckListRepository projectCheckListRepository;

	@Override
	public ProjectCheckList save(ProjectCheckListCreateRequest projectCheckListCreateRequest) {

		return projectCheckListRepository.save(projectCheckListCreateRequest.toEntity());
	}

	@Override
	public Optional<ProjectCheckList> findById(UUID checkListId) {
		return projectCheckListRepository.findById(checkListId);
	}
}
