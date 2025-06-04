package kr.mywork.interfaces.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mywork.common.api.support.error.CommonErrorType;
import kr.mywork.interfaces.auth.dto.request.LoginWebRequest;
import kr.mywork.domain.auth.dto.LoginTrialAuthenticationToken;
import kr.mywork.interfaces.auth.exception.CommonAuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	private final ObjectMapper objectMapper;

	public JwtLoginFilter(String defaultFilterProcessesUrl, ObjectMapper objectMapper) {
		super(defaultFilterProcessesUrl);
		this.objectMapper = objectMapper;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
		throws AuthenticationException {

		try {
			validateContentType(request.getHeader(HttpHeaders.CONTENT_TYPE));

			final LoginWebRequest loginWebRequest = objectMapper.readValue(request.getReader(), LoginWebRequest.class);

			validateLoginRequest(loginWebRequest);

			final LoginTrialAuthenticationToken loginTrialAuthenticationToken = LoginTrialAuthenticationToken.create(
				loginWebRequest.email(), loginWebRequest.password());

			return getAuthenticationManager().authenticate(loginTrialAuthenticationToken);

		} catch (IOException e) {
			throw new CommonAuthenticationException(CommonErrorType.INVALID_JSON_INPUT);
		}
	}

	private void validateContentType(final String contentTypeHeader) {
		if (ObjectUtils.isEmpty(contentTypeHeader) || !contentTypeHeader.toLowerCase().startsWith("application/json")) {
			throw new CommonAuthenticationException(CommonErrorType.INVALID_CONTENT_TYPE);
		}
	}

	private void validateLoginRequest(final LoginWebRequest loginWebRequest) {
		if (ObjectUtils.isEmpty(loginWebRequest.email()) || ObjectUtils.isEmpty(loginWebRequest.password())) {
			throw new CommonAuthenticationException(CommonErrorType.INVALID_INPUT_VALUE);
		}
	}

}
