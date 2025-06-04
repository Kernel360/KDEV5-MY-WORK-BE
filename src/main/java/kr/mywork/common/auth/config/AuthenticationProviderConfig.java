package kr.mywork.common.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.mywork.domain.auth.service.JwtAuthenticationProvider;
import kr.mywork.domain.auth.service.MemberDetailService;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AuthenticationProviderConfig {

	private final MemberDetailService memberDetailService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider loginAuthenticationProvider() {
		return new JwtAuthenticationProvider(memberDetailService, passwordEncoder());
	}
}