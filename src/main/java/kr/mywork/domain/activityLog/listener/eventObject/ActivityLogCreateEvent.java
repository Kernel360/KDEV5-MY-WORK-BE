package kr.mywork.domain.activityLog.listener.eventObject;

import kr.mywork.common.auth.components.dto.LoginMemberDetail;

public record ActivityLogCreateEvent(Object created, LoginMemberDetail loginMemberDetail) {
}
