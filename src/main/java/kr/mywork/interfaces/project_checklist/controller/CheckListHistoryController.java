package kr.mywork.interfaces.project_checklist.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.project_checklist.service.CheckListHistoryService;
import kr.mywork.domain.project_checklist.service.dto.response.CheckListHistorySelectResponse;
import kr.mywork.interfaces.project_checklist.controller.dto.response.CheckListHistorySelectWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CheckListHistoryController {

	private final CheckListHistoryService checkListHistoryService;

	@GetMapping("/projects/checkLists/{checkListId}/histories")
	public ApiResponse<List<CheckListHistorySelectWebResponse>> findAllByCheckListId(@PathVariable UUID checkListId) {
		final List<CheckListHistorySelectResponse> checkListHistorySelectResponses =
			checkListHistoryService.findByCheckListId(checkListId);

		final List<CheckListHistorySelectWebResponse> checkListHistorySelectWebResponses = checkListHistorySelectResponses.stream()
			.map(CheckListHistorySelectWebResponse::fromServiceDto)
			.toList();

		return ApiResponse.success(checkListHistorySelectWebResponses);
	}
}
