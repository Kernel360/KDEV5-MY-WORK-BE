package kr.mywork.interfaces.project_member.controller.dto.response;

import java.util.List;

import kr.mywork.domain.project_member.service.dto.response.CompanyMemberInProjectResponse;
import kr.mywork.domain.project_member.service.dto.response.CompanyMemberInProjectSelectResponse;

public record CompanyMembersInProjectWebResponse(
	List<CompanyMemberInProjectSelectResponse> members
) {
	public static CompanyMembersInProjectWebResponse from(List<CompanyMemberInProjectResponse> responseList) {
		List<CompanyMemberInProjectSelectResponse> members = responseList.stream()
			.map(CompanyMemberInProjectSelectResponse::from)
			.toList();
		return new CompanyMembersInProjectWebResponse(members);
	}
}
