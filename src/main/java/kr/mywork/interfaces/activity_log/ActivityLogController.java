package kr.mywork.interfaces.activity_log;

import static kr.mywork.interfaces.company.controller.CompanyController.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.activityLog.model.ActionType;
import kr.mywork.domain.activityLog.model.TargetType;
import kr.mywork.domain.activityLog.service.ActivityLogService;
import kr.mywork.domain.activityLog.service.dto.response.ActivityLogSelectResponse;
import kr.mywork.interfaces.activity_log.dto.response.ActivityLogListResponse;
import kr.mywork.interfaces.activity_log.dto.response.ActivityLogSelectWebResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/activity-logs")
@RequiredArgsConstructor
public class ActivityLogController {

	private final ActivityLogService activityLogService;

	@GetMapping
	ApiResponse<ActivityLogListResponse> findMembersByOffset(
		@RequestParam(name = "page", required = false) @Min(value = 1, message = "{invalid.page-size}") final int page,
		@RequestParam(name = "companyType", required = false) @Pattern(regexp = COMPANY_TYPE_REGX, message = "{invalid.company-type}") final String companyType,
		@RequestParam(name = "targetType", required = false) TargetType targetType,
		@RequestParam(name = "actionType", required = false) ActionType actionType
		) {

		final List<ActivityLogSelectResponse> activityLogSelectResponses =
			activityLogService.findActivityLogByConditionWithPaging(page, companyType, targetType, actionType);

		final Long totalCount = activityLogService.countTotalActivityLogByCondition(companyType, targetType, actionType);

		List<ActivityLogSelectWebResponse> activityLogSelectWebResponses =
			activityLogSelectResponses.stream().map(ActivityLogSelectWebResponse::from).toList();

		return ApiResponse.success(new ActivityLogListResponse(activityLogSelectWebResponses, totalCount));
	}

}
