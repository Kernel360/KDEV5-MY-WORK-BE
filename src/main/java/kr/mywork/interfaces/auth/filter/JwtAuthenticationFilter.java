package kr.mywork.interfaces.auth.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.auth.dto.MemberDetails;
import kr.mywork.domain.auth.errors.AuthErrorType;
import kr.mywork.domain.auth.errors.AuthException;
import kr.mywork.domain.auth.service.JwtTokenProvider;
import kr.mywork.domain.auth.service.TokenAuthenticationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;
	private final TokenAuthenticationService tokenAuthenticationService;
	private final ObjectMapper objectMapper;

	public JwtAuthenticationFilter(
		JwtTokenProvider jwtTokenProvider,
		TokenAuthenticationService tokenAuthenticationService,
		ObjectMapper objectMapper
	) {
		this.jwtTokenProvider = jwtTokenProvider;
		this.tokenAuthenticationService = tokenAuthenticationService;
		this.objectMapper = objectMapper;
	}

	@Override
	protected void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain
	) throws ServletException, IOException {
		try {
			String token = jwtTokenProvider.resolveAccessToken(request.getHeader(HttpHeaders.AUTHORIZATION));

			if (token != null && jwtTokenProvider.validateAccessToken(token)
				&& SecurityContextHolder.getContext().getAuthentication() == null) {

				MemberDetails memberDetails = tokenAuthenticationService.extractMemberDetailsFromAccessToken(token);

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					memberDetails,
					null,
					memberDetails.getAuthorities()
				);

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}

			filterChain.doFilter(request, response);

		} catch (AuthException authException) {
			log.warn("auth exception name : {}, error type: {}", authException.getClass().getName(),
				authException.getErrorType());

			response.setCharacterEncoding(StandardCharsets.UTF_8.name());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);

			var errorResponse = ApiResponse.error(
				AuthErrorType.AUTHENTICATION_FAILED.getErrorCode().name(),
				AuthErrorType.AUTHENTICATION_FAILED.getMessage());

			objectMapper.writeValue(response.getWriter(), errorResponse);
		}
	}
}
