package kr.mywork.domain.auth.dto;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class LoginSuccessAuthenticationToken extends AbstractAuthenticationToken {

	private final Object principal;
	private final Object credentials;

	private LoginSuccessAuthenticationToken(
		final Object principal, Collection<? extends GrantedAuthority> authorities, Object credentials) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		setAuthenticated(true);
	}


	public static LoginSuccessAuthenticationToken create(
		Object principal,
		Collection<? extends GrantedAuthority> authorities
	) {
		return new LoginSuccessAuthenticationToken(principal, null, authorities);
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

	@Override
	public Object getCredentials() {
		return credentials;
	}
}
