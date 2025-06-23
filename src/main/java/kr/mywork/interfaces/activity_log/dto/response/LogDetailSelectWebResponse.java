package kr.mywork.interfaces.activity_log.dto.response;

import kr.mywork.domain.activityLog.model.FieldType;
import kr.mywork.domain.activityLog.model.LogDetail;
import kr.mywork.domain.activityLog.service.dto.response.LogDetailSelectResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LogDetailSelectWebResponse {

	private final FieldType fieldType;

	private final String oldValue;

	private final String newValue;

	public static LogDetailSelectWebResponse from(final LogDetailSelectResponse logDetail) {
		return new LogDetailSelectWebResponse(logDetail.getFieldType(), logDetail.getOldValue(), logDetail.getNewValue());
	}

}