package kr.mywork.interfaces.member.controller.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import kr.mywork.domain.member.service.dto.response.CompanyMemberResponse;

public record CompanyMemberWebResponse(
	long total,
	List<MemberListWebResponse> members
) {

	public static CompanyMemberWebResponse from(
		List<CompanyMemberResponse> serviceDtos,
		long total
	){
		List<MemberListWebResponse> webDtos = serviceDtos.stream()
			.map(MemberListWebResponse::fromService)
			.collect(Collectors.toList());
		return new CompanyMemberWebResponse(total, webDtos);
	}
}
