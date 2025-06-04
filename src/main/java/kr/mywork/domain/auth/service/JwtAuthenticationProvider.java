package kr.mywork.domain.auth.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.mywork.domain.auth.dto.MemberDetails;
import kr.mywork.domain.auth.dto.LoginSuccessAuthenticationToken;
import kr.mywork.domain.auth.dto.LoginTrialAuthenticationToken;
import kr.mywork.domain.auth.errors.AuthErrorType;
import kr.mywork.domain.auth.errors.AuthException;

public class JwtAuthenticationProvider implements AuthenticationProvider {

	private final MemberDetailService memberDetailService;
	private final PasswordEncoder passwordEncoder;

	public JwtAuthenticationProvider(MemberDetailService memberDetailService, PasswordEncoder passwordEncoder) {
		this.memberDetailService = memberDetailService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		LoginTrialAuthenticationToken authToken = (LoginTrialAuthenticationToken) authentication;

		String email = authToken.getPrincipal().toString();
		String rawPassword = authToken.getCredentials().toString();

		MemberDetails memberDetails = memberDetailService.loadUserByUsername(email);

		if (!passwordEncoder.matches(rawPassword, memberDetails.getPassword())) {
			throw new AuthException(AuthErrorType.AUTHENTICATION_FAILED);
		}


		return LoginSuccessAuthenticationToken.create(memberDetails, memberDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return LoginTrialAuthenticationToken.class.isAssignableFrom(authentication);
	}
}