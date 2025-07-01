package kr.mywork.domain.auth.dto;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class LoginTrialAuthenticationToken extends AbstractAuthenticationToken {

	private final Object principal;
	private final Object credentials;

	public LoginTrialAuthenticationToken(Object principal, Object credentials) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		setAuthenticated(false);
	}

	public LoginTrialAuthenticationToken(Object principal, Object credentials,
		Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		setAuthenticated(true);
	}

	public static LoginTrialAuthenticationToken create(final Object principal, final Object credentials) {
		return new LoginTrialAuthenticationToken(principal, credentials);
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}

	@Override
	public Object getCredentials() {
		return this.credentials;
	}

}
