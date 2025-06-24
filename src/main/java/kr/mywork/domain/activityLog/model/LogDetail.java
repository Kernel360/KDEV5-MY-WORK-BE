package kr.mywork.domain.activityLog.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import kr.mywork.common.rdb.id.UnixTimeOrderedUuidGeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LogDetail {

	@Id
	@UnixTimeOrderedUuidGeneratedValue
	private UUID id;

	private UUID activityLogId;  // ← 이게 있으면 좋겠음!

	@Enumerated(EnumType.STRING)
	private FieldType fieldType; // 타입 : (POST_TITLE / POST_CONTENT / POST_APPROVAL / CHECK_LIST_APPROVAL)

	private String oldValue;

	private String newValue;

	public LogDetail(UUID activityLogId, FieldType fieldType, String oldValue, String newValue) {
		this.activityLogId = activityLogId;
		this.fieldType = fieldType;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
}