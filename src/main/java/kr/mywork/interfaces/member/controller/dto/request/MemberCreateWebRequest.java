package kr.mywork.interfaces.member.controller.dto.request;

import kr.mywork.domain.member.service.dto.request.MemberCreateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class MemberCreateWebRequest {


    private final String name; // 이름
    private final String department; // 부서
    private final String position; // 직급
    private final String phoneNumber; // 담당자 번호
    private final String email; // 이메일 (로그인 아이디)
    private final String birthday;
    private final String type;

    public MemberCreateRequest toServiceDto() {
        return new MemberCreateRequest(this.name, this.department, this.position, this.phoneNumber, this.email, this.birthday, this.type);
    }
}
