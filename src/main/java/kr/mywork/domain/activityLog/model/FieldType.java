package kr.mywork.domain.activityLog.model;

import java.util.Arrays;

import kr.mywork.domain.activityLog.errors.ActivityLogErrorType;
import kr.mywork.domain.activityLog.errors.TargetTypeNotFoundException;

public enum FieldType {
	POST_TITLE,
	POST_CONTENT,
	POST_APPROVAL,
	CHECK_LIST_APPROVAL,
	PROJECT_NAME,
	PROJECT_START_AT,
	PROJECT_END_AT,
	PROJECT_DETAIL,
	PROJECT_STEP_TITLE,
	PROJECT_STEP_ORDER_NUM,
	REVIEW_COMMENT,
	PROJECT_CHECK_LIST_TITLE,
	PROJECT_CHECK_LIST_APPROVAL,
	COMPANY_NAME,
	COMPANY_DETAIL,
	COMPANY_BUSINESS_NUMBER,
	COMPANY_ADDRESS,
	COMPANY_TYPE,
	COMPANY_CONTACT_PHONE_NUMBER,
	COMPANY_CONTACT_EMAIL,
	COMPANY_LOGO_IMAGE_PATH,
	MEMBER_NAME,
	MEMBER_COMPANY_NAME,
	MEMBER_DEPARTMENT,
	MEMBER_POSITION,
	MEMBER_ROLE,
	MEMBER_PHONE_NUMBER,
	MEMBER_EMAIL,
	UNKNOWN_FIELD_TYPE;

	// public static FieldType from(String value) {
	// 	return Arrays.stream(FieldType.values())
	// 		.filter(fieldType -> fieldType.name().equals(value))
	// 		.findAny()
	// 		.orElseThrow(() -> new TargetTypeNotFoundException(ActivityLogErrorType.FIELD_TYPE_NOT_FOUND));
	// }
}
