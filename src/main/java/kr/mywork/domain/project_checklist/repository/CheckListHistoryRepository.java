package kr.mywork.domain.project_checklist.repository;

import java.util.List;
import java.util.UUID;

import kr.mywork.domain.project_checklist.model.CheckListHistory;
import kr.mywork.domain.project_checklist.service.dto.response.CheckListHistorySelectResponse;

public interface CheckListHistoryRepository {
	CheckListHistory save(CheckListHistory checkListHistory);
	List<CheckListHistorySelectResponse> findAllByCheckListId(UUID checkListId);
}
