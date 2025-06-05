package kr.mywork.interfaces.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mywork.common.api.support.response.ApiResponse;
import kr.mywork.domain.auth.dto.response.TokenResponse;
import kr.mywork.interfaces.auth.dto.response.TokenWebResponse;
import kr.mywork.domain.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

	private final TokenService tokenService;

	@PostMapping("/logout")
	public ApiResponse<Void> logout(HttpServletRequest request, HttpServletResponse response) {
		tokenService.logout(request, response);
		return ApiResponse.success((Void) null);
	}

	@PostMapping("/reissue")
	public ApiResponse<TokenWebResponse> reissue(HttpServletRequest request, HttpServletResponse response) {
		TokenResponse tokenResponse = tokenService.reissue(request, response);
		return ApiResponse.success(TokenWebResponse.from(tokenResponse));
	}
}
