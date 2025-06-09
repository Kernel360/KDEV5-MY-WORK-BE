package kr.mywork.interfaces.member.controller.dto.response;

import java.util.UUID;

import kr.mywork.domain.member.service.dto.response.MemberProjectInfoResponse;

public record MemberProjectInfoWebResponse(UUID projectId, String projectName) {

	public static MemberProjectInfoWebResponse from(final MemberProjectInfoResponse response) {
		return new MemberProjectInfoWebResponse(response.projectId(), response.projectName());
	}
}
