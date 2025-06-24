package kr.mywork.domain.project_checklist.repository;

import kr.mywork.domain.project_checklist.model.CheckListHistory;

public interface CheckListHistoryRepository {
	CheckListHistory save(CheckListHistory checkListHistory);
}
