package kr.mywork.infrastructure.project_checklist.rdb;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mywork.domain.project_checklist.model.CheckListHistory;

public interface JpaCheckListRepository extends JpaRepository<CheckListHistory, UUID> {
}
