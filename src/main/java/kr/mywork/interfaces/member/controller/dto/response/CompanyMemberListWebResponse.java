package kr.mywork.interfaces.member.controller.dto.response;

import kr.mywork.domain.member.service.dto.response.CompanyMemberResponse;

public record CompanyMemberListWebResponse(String id, String name, String phoneNumber, String position, String department, String role, String email) {
	/**
	 * 서비스-레벨 DTO(CompanyMemberResponse)를 웹-레벨 DTO로 변환
	 */
	public static CompanyMemberListWebResponse fromService(CompanyMemberResponse response) {
		return new CompanyMemberListWebResponse(response.id().toString(), response.name(), response.phoneNumber(),
			response.position(), response.department(), response.role(), response.email());
	}
}
