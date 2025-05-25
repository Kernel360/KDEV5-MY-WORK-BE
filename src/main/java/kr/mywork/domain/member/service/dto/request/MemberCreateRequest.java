package kr.mywork.domain.member.service.dto.request;

import kr.mywork.domain.member.model.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
public class MemberCreateRequest {

    private final String name; // 이름
    private final String department; // 부서
    private final String position; // 직급
    private final String phoneNumber; // 담당자 번호
    private final String email; // 이메일 (로그인 아이디)
    private final String type;
    @Getter
    private final String birthday;
    @Setter
    private String password;


    public Member toEntity() {
        return new Member(this.name,this.department,this.position,this.phoneNumber,
        this.email,this.birthday, this.type);
    }
}
