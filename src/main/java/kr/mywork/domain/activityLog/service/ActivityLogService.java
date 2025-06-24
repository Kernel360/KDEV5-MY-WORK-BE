package kr.mywork.domain.activityLog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.mywork.domain.activityLog.listener.eventObject.CreateEventObject;
import kr.mywork.domain.activityLog.listener.eventObject.DeleteEventObject;
import kr.mywork.domain.activityLog.listener.eventObject.ModifyEventObject;
import kr.mywork.domain.activityLog.model.ActionType;
import kr.mywork.domain.activityLog.model.ActivityLog;
import kr.mywork.domain.activityLog.model.LogDetail;
import kr.mywork.domain.activityLog.model.TargetType;
import kr.mywork.domain.activityLog.repository.ActivityLogRepository;
import kr.mywork.domain.activityLog.service.dto.response.ActivityLogSelectResponse;
import kr.mywork.domain.activityLog.service.dto.response.LogDetailSelectResponse;
import kr.mywork.domain.company.model.Company;
import kr.mywork.domain.member.model.Member;
import kr.mywork.domain.post.model.Post;
import kr.mywork.domain.post.model.Review;
import kr.mywork.domain.project.model.Project;
import kr.mywork.domain.project.model.ProjectMember;
import kr.mywork.domain.project_checklist.model.ProjectCheckList;
import kr.mywork.domain.project_step.model.ProjectStep;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityLogService {

	@Value("${activityLog.page.size}")
	private int activityLogPageSize;

	private final ActivityLogRepository activityLogRepository;
	private final LogDetailService logDetailService;


	// 액티비티 로그 객체 생성 - 생성 액션 전용
	public ActivityLog makeCreatedActivityLog(CreateEventObject event) {
		TargetType targetType = determineTargetType(event.getCreated());

		// 적절한 타입으로 캐스팅
		LocalDateTime createdAt = extractCreatedDateTime(event.getCreated());
		UUID targetId = extractTargetId(event.getCreated());

		return new ActivityLog(
			createdAt,
			ActionType.CREATE,
			targetType,
			targetId,
			event.getLoginMemberDetail().memberId(),
			event.getLoginMemberDetail().memberName(),
			event.getLoginMemberDetail().companyId(),
			event.getLoginMemberDetail().companyName(),
			event.getLoginMemberDetail().companyType()
		);
	}

	// 액티비티 로그 객체 생성 - 수정 액션 전용
	public ActivityLog makeModifiedActivityLog(ModifyEventObject event) {
		TargetType targetType = determineTargetType(event.getBefore());

		LocalDateTime modifiedAt = extractModifiedDateTime(event.getAfter());
		UUID targetId = extractTargetId(event.getAfter());

		return new ActivityLog(
			modifiedAt,
			ActionType.MODIFY,
			targetType,
			targetId,
			event.getLoginMemberDetail().memberId(),
			event.getLoginMemberDetail().memberName(),
			event.getLoginMemberDetail().companyId(),
			event.getLoginMemberDetail().companyName(),
			event.getLoginMemberDetail().companyType()
		);
	}

	// 액티비티 로그 객체 생성 - 삭제 액션 전용
	public ActivityLog makeDeletedActivityLog(DeleteEventObject event) {
		TargetType targetType = determineTargetType(event.getDeleted());

		// 적절한 타입으로 캐스팅
		LocalDateTime deletedAt = extractModifiedDateTime(event.getDeleted());
		UUID targetId = extractTargetId(event.getDeleted());

		return new ActivityLog(
			deletedAt,
			ActionType.DELETE,
			targetType,
			targetId,
			event.getLoginMemberDetail().memberId(),
			event.getLoginMemberDetail().memberName(),
			event.getLoginMemberDetail().companyId(),
			event.getLoginMemberDetail().companyName(),
			event.getLoginMemberDetail().companyType()
		);
	}

	public List<ActivityLogSelectResponse> findActivityLogByConditionWithPaging(
		final int page, String companyType, TargetType targetType, ActionType actionType) {

		List<ActivityLogSelectResponse> activityLogSelectResponses = activityLogRepository.findActivityLogBySearchConditionWithPaging(
			page, activityLogPageSize, companyType, targetType, actionType);

		for (ActivityLogSelectResponse activityLogSelectResponse : activityLogSelectResponses) {

			List<LogDetail> logDetails = logDetailService.findByActivityLogId(activityLogSelectResponse.getId());

			List<LogDetailSelectResponse> logDetailSelectResponses = logDetails.stream().map(LogDetailSelectResponse::from).toList();

			activityLogSelectResponse.attachLogDetails(logDetailSelectResponses);
		}

		return activityLogSelectResponses;
	}

	public Long countTotalActivityLogByCondition(
		String companyType, TargetType targetType, ActionType actionType) {

		return activityLogRepository.countTotalActivityLogByCondition(companyType, targetType, actionType);
	}

	// 대상 타입 결정
	public TargetType determineTargetType(Object object) {
		String className = object.getClass().getName();

		if (className.equals(Project.class.getName())) {
			return TargetType.PROJECT;
		}
		if (className.equals(Post.class.getName())) {
			return TargetType.POST;
		}
		if (className.equals(Review.class.getName())) {
			return TargetType.REVIEW;
		}
		if (className.equals(ProjectCheckList.class.getName())) {
			return TargetType.PROJECT_CHECK_LIST;
		}
		if (className.equals(Member.class.getName())) {
			return TargetType.MEMBER;
		}
		if (className.equals(Company.class.getName())) {
			return TargetType.COMPANY;
		}
		if (className.equals(ProjectStep.class.getName())) {
			return TargetType.PROJECT_STEP;
		}
		if (className.equals(ProjectMember.class.getName())) {
			return TargetType.PROJECT_MEMBER;
		}

		return TargetType.UNKNOWN;
	}

	// 수정 시간 추출
	private LocalDateTime extractModifiedDateTime(Object object) {
		if (object instanceof Project) {
			return ((Project) object).getModifiedAt();
		}
		if (object instanceof Post) {
			return ((Post) object).getModifiedAt();
		}
		if (object instanceof ProjectStep) {
			return ((ProjectStep) object).getModifiedAt();
		}
		if (object instanceof Review) {
			return ((Review) object).getModifiedAt();
		}
		if (object instanceof ProjectCheckList) {
			return ((ProjectCheckList) object).getModifiedAt();
		}
		if (object instanceof Company) {
			return ((Company) object).getModifiedAt();
		}
		if (object instanceof Member) {
			return ((Member) object).getModifiedAt();
		}
		if (object instanceof ProjectMember) {
			return ((ProjectMember) object).getModifiedAt();
		}

		return null; // 기본값
	}

	// 생성 시간 추출
	private LocalDateTime extractCreatedDateTime(Object object) {
		if (object instanceof Project) {
			return ((Project) object).getCreatedAt();
		}
		if (object instanceof Post) {
			return ((Post) object).getCreatedAt();
		}
		if (object instanceof ProjectStep) {
			return ((ProjectStep) object).getCreatedAt();
		}
		if (object instanceof Review) {
			return ((Review) object).getCreatedAt();
		}
		if (object instanceof ProjectCheckList) {
			return ((ProjectCheckList) object).getCreatedAt();
		}
		if (object instanceof Company) {
			return ((Company) object).getCreatedAt();
		}
		if (object instanceof Member) {
			return ((Member) object).getCreatedAt();
		}
		if (object instanceof ProjectMember) {
			return ((ProjectMember) object).getCreatedAt();
		}

		return null; // 기본값
	}

	// 대상 ID 추출
	private UUID extractTargetId(Object object) {
		if (object instanceof Project) {
			return ((Project) object).getId();
		}
		if (object instanceof Post) {
			return ((Post) object).getId();
		}
		if (object instanceof ProjectStep) {
			return ((ProjectStep) object).getId();
		}
		if (object instanceof Review) {
			return ((Review) object).getId();
		}
		if (object instanceof ProjectCheckList) {
			return ((ProjectCheckList) object).getId();
		}
		if (object instanceof Company) {
			return ((Company) object).getId();
		}
		if (object instanceof Member) {
			return ((Member) object).getId();
		}
		if (object instanceof ProjectMember) {
			return ((ProjectMember) object).getId();
		}

		return null; // 기본값
	}
}
