package kr.mywork.domain.activityLog.service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import kr.mywork.domain.activityLog.listener.eventObject.ActivityModifyEvent;
import kr.mywork.domain.activityLog.model.FieldType;
import kr.mywork.domain.activityLog.model.LogDetail;
import kr.mywork.domain.activityLog.repository.LogDetailRepository;
import kr.mywork.domain.company.model.Company;
import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.post.model.Post;
import kr.mywork.domain.post.model.Review;
import kr.mywork.domain.project.model.Project;
import kr.mywork.domain.project_checklist.model.ProjectCheckList;
import kr.mywork.domain.project_step.model.ProjectStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogDetailService {

	private final LogDetailRepository logDetailRepository;

	public List<LogDetail> makeLogDetails(ActivityModifyEvent event, UUID activityLogId) {

		Class<?> clazz = event.before().getClass();

		List<LogDetail> diffValue = new ArrayList<>();

		for (Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);
			try {

				Object beforeValue = field.get(event.before());
				Object afterValue = field.get(event.after());

				if (!java.util.Objects.equals(beforeValue, afterValue)) {
					final String fieldName = field.getName();

					if (fieldName.equals("modifiedAt") || fieldName.equals("password") ||
						fieldName.equals("deleted") || fieldName.equals("createdAt") || fieldName.equals("id"))
						continue;

					FieldType fieldType = typeCheckAndReturn(event.before(), field.getName());

					diffValue.add(new LogDetail(activityLogId, fieldType, convertToString(beforeValue), convertToString(afterValue)));
				}
			} catch (IllegalAccessException e) { // 필드 접근 불가한 경우, 접근 불가 필드 제외하고 로그 기록하게 함.
				log.warn("필드 접근 불가: {}", field.getName());
				continue;
			}
		}

		return diffValue;
	}

	// 액션 대상에 대한 상세 타입을 반환함.
	public FieldType typeCheckAndReturn(final Object object, final String fieldName) {
		Class<?> clazz = object.getClass();

		if (clazz.getName().equals(Project.class.getName())) {
			switch (fieldName) {
				case "name" -> {
					return FieldType.PROJECT_NAME;
				}
				case "detail" -> {
					return FieldType.PROJECT_DETAIL;
				}
				case "startAt" -> {
					return FieldType.PROJECT_START_AT;
				}
				case "endAt" -> {
					return FieldType.PROJECT_END_AT;
				}
			}
		}

		if (clazz.getName().equals(ProjectStep.class.getName())) {
			switch (fieldName) {
				case "title" -> {
					return FieldType.PROJECT_STEP_TITLE;
				}
				case "orderNum" -> {
					return FieldType.PROJECT_STEP_ORDER_NUM;
				}
			}
		}

		if (clazz.getName().equals(Post.class.getName())) {
			switch (fieldName) {
				case "title" -> {
					return FieldType.POST_TITLE;
				}
				case "content" -> {
					return FieldType.POST_CONTENT;
				}
				case "approval" -> {
					return FieldType.POST_APPROVAL;
				}
			}
		}

		if (clazz.getName().equals(Review.class.getName())) {
			switch (fieldName) {
				case "comment" -> {
					return FieldType.REVIEW_COMMENT;
				}
			}
		}

		if (clazz.getName().equals(ProjectCheckList.class.getName())) {
			switch (fieldName) {
				case "title" -> {
					return FieldType.PROJECT_CHECK_LIST_TITLE;
				}
				case "approval" -> {
					return FieldType.PROJECT_CHECK_LIST_APPROVAL;
				}
			}
		}

		if (clazz.getName().equals(Company.class.getName())) {
			switch (fieldName) {
				case "name" -> {
					return FieldType.COMPANY_NAME;
				}
				case "detail" -> {
					return FieldType.COMPANY_DETAIL;
				}
				case "businessNumber" -> {
					return FieldType.COMPANY_BUSINESS_NUMBER;
				}
				case "address" -> {
					return FieldType.COMPANY_ADDRESS;
				}
				case "type" -> {
					return FieldType.COMPANY_TYPE;
				}
				case "contactPhoneNumber" -> {
					return FieldType.COMPANY_CONTACT_PHONE_NUMBER;
				}
				case "contactEmail" -> {
					return FieldType.COMPANY_CONTACT_EMAIL;
				}
				case "logoImagePath" -> {
					return FieldType.COMPANY_LOGO_IMAGE_PATH;
				}
			}
		}

		if (clazz.getName().equals(Member.class.getName())) {
			switch (fieldName) {
				case "name" -> {
					return FieldType.MEMBER_NAME;
				}
				case "department" -> {
					return FieldType.MEMBER_DEPARTMENT;
				}
				case "position" -> {
					return FieldType.MEMBER_POSITION;
				}
				case "role" -> {
					return FieldType.MEMBER_ROLE;
				}
				case "phoneNumber" -> {
					return FieldType.MEMBER_PHONE_NUMBER;
				}
				case "email" -> {
					return FieldType.MEMBER_EMAIL;
				}
			}
		}

		return FieldType.UNKNOWN_FIELD_TYPE;
	}

	// 어떤 객체든 string으로 변환, 이유 : 로그 저장할 때 다 문자열로 저장하기 위해서
	private String convertToString(Object value) {
		if (value == null) {
			return null;
		}

		if (value instanceof LocalDateTime) {
			return ((LocalDateTime) value).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		}

		if (value instanceof Enum) {
			return ((Enum<?>) value).name();
		}

		if (value instanceof Boolean) {
			return ((Boolean) value) ? "TRUE" : "FALSE";
		}

		if (value instanceof Number) {
			return value.toString();
		}

		if (value instanceof UUID) {
			return value.toString();
		}

		return value.toString();
	}

	public void saveAll(List<LogDetail> diffValue) {
		logDetailRepository.saveAll(diffValue);
	}

	public List<LogDetail> findByActivityLogId(UUID id) {
		return logDetailRepository.findByActivityLogId(id);
	}
}
