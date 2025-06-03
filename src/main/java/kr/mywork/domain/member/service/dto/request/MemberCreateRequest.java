package kr.mywork.domain.member.service.dto.request;

import kr.mywork.domain.member.model.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

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
    private final String password;


    public Member toEntity() {
        return new Member(
            this.id,
            this.companyId,
            this.name,
            this.department,
            this.position,
            this.role,
            this.phoneNumber,
            this.email,
            this.password,
            this.birthDate

        );

    }
}
