package kr.mywork.domain.activityLog.listener.eventObject;

import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ModifyEventObject {

	private final Object before;
	private final Object after;
	private final LoginMemberDetail loginMemberDetail;

}
