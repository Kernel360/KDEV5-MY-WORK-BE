package kr.mywork.interfaces.project_checklist.controller.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CheckListsSelectWebResponse(
	@JsonProperty("projectCheckLists") List<ProjectCheckListSelectWebResponse> projectCheckListSelectWebResponses) {
}
