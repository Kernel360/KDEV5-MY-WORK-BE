package kr.mywork.interfaces.project.dto.response;

import kr.mywork.domain.project.service.dto.response.ProjectMemberResponse;

public record ProjectMemberWebResponse(
	String memberId,
	String memberName
) {
	public static ProjectMemberWebResponse fromService(ProjectMemberResponse response) {
		return new ProjectMemberWebResponse(
			response.memberId().toString(),
			response.memberName()
		);
	}
}

