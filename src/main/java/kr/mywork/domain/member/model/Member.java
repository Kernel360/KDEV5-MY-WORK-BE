package kr.mywork.domain.member.model;

import jakarta.persistence.*;
import kr.mywork.domain.company.model.CompanyType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(length = 30, nullable = false)
    private String name; // 이름

    @Column(length = 100)
    private String department; // 부서

    @Column(length = 50)
    private String position; // 직급

    @Column(length = 20)
    private String role; // 권한

    @Column(name = "phone_number", length = 300)
    private String phoneNumber; // 담당자 번호

    @Column(length = 300, unique = true)
    private String email; // 이메일 (로그인 아이디)

    @Column(length = 300)
    private String password; // 패스워드

    @Column(nullable = false , length = 300)
    private String birthday;

    @Column(nullable = false)
    private Boolean deleted = false; // 멤버 삭제 여부

    @Column(nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @Column(nullable = false, length = 10)
    @Enumerated(value = jakarta.persistence.EnumType.STRING)
    private MemberType type;


    public Member(final String name, final String department, final String position,
                  final String phoneNumber, final String email, final String type, final String birthday) {
        this.name = name;
        this.department = department;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.type = MemberType.from(type);
        this.birthday = birthday;
    }


}
