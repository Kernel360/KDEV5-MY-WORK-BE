package kr.mywork.domain.activityLog.model;

import java.util.Arrays;

import kr.mywork.domain.activityLog.errors.ActivityLogErrorType;
import kr.mywork.domain.activityLog.errors.TargetTypeNotFoundException;

public enum TargetType {
	POST, REVIEW, PROJECT_CHECK_LIST, MEMBER, COMPANY, PROJECT, PROJECT_STEP, PROJECT_MEMBER, UNKNOWN;

	// public static TargetType from(String value) {
	// 	return Arrays.stream(TargetType.values())
	// 		.filter(targetType -> targetType.name().equals(value))
	// 		.findAny()
	// 		.orElseThrow(() -> new TargetTypeNotFoundException(ActivityLogErrorType.TARGET_LOG_NOT_FOUND));
	// }
}
