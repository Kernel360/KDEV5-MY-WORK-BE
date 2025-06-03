package kr.mywork.interfaces.member.controller.dto.response;

import java.util.List;

public record CompanyMemberWebResponse(
	long total,
	List<CompanyMemberListWebResponse> members
) {
}
