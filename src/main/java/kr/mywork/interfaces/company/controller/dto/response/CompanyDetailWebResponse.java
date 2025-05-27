package kr.mywork.interfaces.company.controller.dto.response;

import kr.mywork.domain.company.model.Company;
import kr.mywork.domain.member.model.Member;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 회사 상세 정보 + 소속 멤버 리스트를 포함한 웹 응답 DTO
 */
public record CompanyDetailWebResponse(
        UUID companyId,
        String name,
        String detail,
        String businessNumber,
        String address,
        String type,
        String contactPhoneNumber,
        String contactEmail,
        String logoImagePath,
        List<MemberWebResponse> members
) {
    public static CompanyDetailWebResponse from(Company company, List<Member> members) {
        List<MemberWebResponse> memberResponses = members.stream()
                .map(MemberWebResponse::from)
                .collect(Collectors.toList());

        return new CompanyDetailWebResponse(
                company.getId(),
                company.getName(),
                company.getDetail(),
                company.getBusinessNumber(),
                company.getAddress(),
                company.getType().name(),
                company.getContactPhoneNumber(),
                company.getContactEmail(),
                company.getLogoImagePath(),
                memberResponses
        );
    }

    public record MemberWebResponse(
            UUID id,
            String name,
            String department,
            String position,
            String role,
            String phoneNumber,
            String email,
            Boolean deleted
    ) {
        public static MemberWebResponse from(Member member) {
            return new MemberWebResponse(
                    member.getId(),
                    member.getName(),
                    member.getDepartment(),
                    member.getPosition(),
                    member.getRole(),
                    member.getPhoneNumber(),
                    member.getEmail(),
                    member.getDeleted()
            );
        }
    }
}
