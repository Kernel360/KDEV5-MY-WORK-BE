package kr.mywork.common.auth.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import lombok.Getter;

@Getter
@ConfigurationProperties(prefix = "security.allow")
public class CorsProperties {

	private final List<String> origins;
	private final List<String> methods;
	private final List<String> headers;
	private final Boolean credential;

	@ConstructorBinding
	public CorsProperties(final List<String> origins, final List<String> methods, final List<String> headers,
		final Boolean credential) {
		this.origins = origins;
		this.methods = methods;
		this.headers = headers;
		this.credential = credential;
	}
}

