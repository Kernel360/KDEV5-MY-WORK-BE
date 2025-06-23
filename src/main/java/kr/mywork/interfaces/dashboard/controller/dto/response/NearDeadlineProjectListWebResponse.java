package kr.mywork.interfaces.dashboard.controller.dto.response;

import java.util.List;

public record NearDeadlineProjectListWebResponse(
	List<NearDeadlineProjectWebResponse> projects,
	long totalCount
) {}
