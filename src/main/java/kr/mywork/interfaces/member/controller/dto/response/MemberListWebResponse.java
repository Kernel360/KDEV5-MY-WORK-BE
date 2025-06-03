package kr.mywork.interfaces.member.controller.dto.response;

import java.util.List;

public record MemberListWebResponse(List<MemberSelectWebResponse> members, long totalCount) {
}
