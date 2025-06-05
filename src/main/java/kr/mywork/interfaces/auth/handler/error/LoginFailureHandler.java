package kr.mywork.interfaces.auth.handler.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mywork.common.api.support.error.CommonErrorType;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.auth.errors.AuthErrorType;

import org.springframework.http.MediaType;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.core.AuthenticationException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoginFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    public LoginFailureHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException exception) throws IOException {

        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        CommonErrorType errorType = CommonErrorType.UNAUTHORIZED;
        ApiResponse<?> apiResponse = ApiResponse.error(
            AuthErrorType.ACCESS_DENIED.getErrorCode().name(),
            AuthErrorType.ACCESS_DENIED.getMessage()
        );
        objectMapper.writeValue(response.getWriter(), apiResponse);
    }
}
