package kr.mywork.domain.activityLog.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import kr.mywork.common.rdb.id.UnixTimeOrderedUuidGeneratedValue;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ActivityLog {

	// 객체 고유 ID
	@Id
	@UnixTimeOrderedUuidGeneratedValue
	private UUID id;

	// 액션 동작 시점
	private LocalDateTime actionTime;

	// 액션 정보
	@Enumerated(EnumType.STRING)
	private ActionType actionType; // 타입종류 : 생성(CREATE) / 수정(MODIFY) / 삭제(DELETE)

	@Enumerated(EnumType.STRING)
	private TargetType targetType; // 타입종류 : 게시글(POST) / 댓글(REVIEW) / 결재(PROJECT_CHECK_LIST) / 멤버(MEMBER) / 회사(COMPANY) / 프로젝트(PROJECT) / 단계(PROJECT_STEP)

	private UUID targetId;

	// 액터 정보
	private UUID actorId;

	private String actorName;

	private UUID actorCompanyId;

	private String actorCompanyName;

	private String companyType;

	public ActivityLog(LocalDateTime actionTime, ActionType actionType, TargetType targetType, UUID targetId,
		UUID actorId,
		String actorName, UUID actorCompanyId, String actorCompanyName, String companyType) {
		this.actionTime = actionTime;
		this.actionType = actionType;
		this.targetType = targetType;
		this.targetId = targetId;
		this.actorId = actorId;
		this.actorName = actorName;
		this.actorCompanyId = actorCompanyId;
		this.actorCompanyName = actorCompanyName;
		this.companyType = companyType;
	}
}
