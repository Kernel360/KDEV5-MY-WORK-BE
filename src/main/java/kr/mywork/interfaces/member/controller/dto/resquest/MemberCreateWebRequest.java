package kr.mywork.interfaces.member.controller.dto.resquest;

import kr.mywork.domain.member.service.dto.resquest.MemberCreateRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public record MemberCreateWebRequest(
        UUID id,
        UUID companyId,
        String name,
        String department,
        String position,
        String role,
        String phoneNumber,
        String email,
        LocalDateTime birthDate
) {
    public MemberCreateRequest toServiceDto() {
        String password = birthDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return new MemberCreateRequest(
                id, companyId, name, department, position, role,
                phoneNumber, email, birthDate, password
        );
    }
}