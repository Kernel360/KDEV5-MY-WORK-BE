package kr.mywork.domain.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class MemberDetails implements UserDetails {

    private final UUID id;
    private final String email;
    private final String password;
    private final GrantedAuthority authority;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(authority); // 하나만 감싸서 전달
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public String getAuthorityAsStr() {
        return this.authority.getAuthority();
    }
}
