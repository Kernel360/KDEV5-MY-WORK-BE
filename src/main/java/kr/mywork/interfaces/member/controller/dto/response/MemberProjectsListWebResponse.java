package kr.mywork.interfaces.member.controller.dto.response;

import java.util.List;

public record MemberProjectsListWebResponse(List<MemberProjectInfoWebResponse> projects) {
}
