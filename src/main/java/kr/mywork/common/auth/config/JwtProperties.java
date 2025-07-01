package kr.mywork.common.auth.config;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

	private AccessToken accessToken = new AccessToken();
	private RefreshToken refreshToken = new RefreshToken();

	@Getter
	@Setter
	public static class AccessToken {
		private String privateKey;
		private long expiration;
	}

	@Getter
	@Setter
	public static class RefreshToken {
		private String privateKey;
		private long expiration;
	}
}
