package kr.mywork.domain.activityLog.service.dto.response;

import kr.mywork.domain.activityLog.model.FieldType;
import kr.mywork.domain.activityLog.model.LogDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LogDetailSelectResponse {

	private final FieldType fieldType;

	private final String oldValue;

	private final String newValue;

	public static LogDetailSelectResponse from(final LogDetail logDetail) {
		return new LogDetailSelectResponse(logDetail.getFieldType(), logDetail.getOldValue(), logDetail.getNewValue());
	}

}