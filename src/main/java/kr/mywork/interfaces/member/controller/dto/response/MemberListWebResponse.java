package kr.mywork.interfaces.member.controller.dto.response;

import kr.mywork.domain.member.service.dto.response.CompanyMemberResponse;

public record MemberListWebResponse(String id, String name, String phoneNumber, String position, String department) {
	/**
	 * 서비스-레벨 DTO(CompanyMemberResponse)를 웹-레벨 DTO로 변환
	 */
	public static MemberListWebResponse fromService(CompanyMemberResponse response) {
		return new MemberListWebResponse(response.id().toString(), response.name(), response.phoneNumber(), response.position(), response.department());
	}
}
