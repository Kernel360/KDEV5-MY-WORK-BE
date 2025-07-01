package kr.mywork.domain.activityLog.listener.eventObject;

import kr.mywork.common.auth.components.dto.LoginMemberDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DeleteEventObject {

	private final Object deleted;
	private final LoginMemberDetail loginMemberDetail;

}
