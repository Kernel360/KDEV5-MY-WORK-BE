package kr.mywork.domain.activityLog.model;

import java.util.Arrays;

import kr.mywork.domain.activityLog.errors.ActivityLogErrorType;
import kr.mywork.domain.activityLog.errors.ActionTypeNotFoundException;

public enum ActionType {
	CREATE, MODIFY, DELETE;

	// public static ActionType from(String value) {
	// 	return Arrays.stream(ActionType.values())
	// 		.filter(actionType -> actionType.name().equals(value))
	// 		.findAny()
	// 		.orElseThrow(() -> new ActionTypeNotFoundException(ActivityLogErrorType.ACTION_TYPE_NOT_FOUND));
	// }
}
