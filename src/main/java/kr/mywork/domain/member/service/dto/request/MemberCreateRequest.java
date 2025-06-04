package kr.mywork.domain.member.service.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import kr.mywork.domain.member.model.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberCreateRequest {
    private final UUID id;
    private final UUID companyId;
    private final String name;
    private final String department;
    private final String position;
    private final String role;
    private final String phoneNumber;
    private final String email;
    private final LocalDateTime birthDate;


    public Member toEntity(String encPassword) {
        return new Member(
            this.id,
            this.companyId,
            this.name,
            this.department,
            this.position,
            this.role,
            this.phoneNumber,
            this.email,
            encPassword,
            this.birthDate

        );

    }
}
