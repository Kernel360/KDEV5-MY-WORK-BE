package kr.mywork.domain.auth.dto;

import kr.mywork.domain.company.model.CompanyType;
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
    private final String name;

    private final String email;
    private final String password;
    private final GrantedAuthority authority;
    private final UUID companyId;
    private final String companyName;
    private final String logoImagePath;
    private final String companyType;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(authority);
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
