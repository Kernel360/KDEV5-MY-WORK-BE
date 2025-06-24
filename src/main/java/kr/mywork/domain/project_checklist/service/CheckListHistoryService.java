package kr.mywork.domain.project_checklist.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mywork.domain.project_checklist.repository.CheckListHistoryRepository;
import kr.mywork.domain.project_checklist.service.dto.response.CheckListHistorySelectResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckListHistoryService {

	private final CheckListHistoryRepository checkListHistoryRepository;

	@Transactional
	public List<CheckListHistorySelectResponse> findByCheckListId(final UUID checkListId) {
		return checkListHistoryRepository.findAllByCheckListId(checkListId);
	}
}
