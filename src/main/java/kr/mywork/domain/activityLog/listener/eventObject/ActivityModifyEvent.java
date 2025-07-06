package kr.mywork.domain.activityLog.listener.eventObject;

import kr.mywork.common.auth.components.dto.LoginMemberDetail;

public record ActivityModifyEvent(Object before, Object after, LoginMemberDetail loginMemberDetail) {

}
