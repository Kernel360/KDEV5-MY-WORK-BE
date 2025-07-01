package kr.mywork.interfaces.activity_log.dto.response;

import java.util.List;

public record ActivityLogListResponse (
	List<ActivityLogSelectWebResponse> activityLogSelectWebResponses, Long totalCount) {
}
