package kr.mywork.domain.member.service.dto.response;

import java.util.UUID;

import kr.mywork.domain.member.model.Member;

public record CompanyMemberResponse(
	UUID id,
	String name,
	String phoneNumber,
	String position,
	String department

){
	public static CompanyMemberResponse fromEntity(Member member) {
	return new CompanyMemberResponse(
		member.getId(),
		member.getName(),
		member.getPhoneNumber(),
		member.getPosition(),
		member.getDepartment());
	}
}
