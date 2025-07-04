package kr.mywork.common.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mywork.common.api.components.interceptor.PermissionCheckInterceptor;
import kr.mywork.domain.project_member.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
	private final ProjectMemberService projectMemberService;
	private final ObjectMapper objectMapper;


	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/static/swagger-ui/");
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**") // 또는 "/**" 전체 허용
					.allowedOrigins("http://localhost:3000")
					.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
					.allowedHeaders("*")
					.allowCredentials(true); // 인증 정보 포함 허용
			}
		};
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(permissionCheckInterceptor())
				.addPathPatterns("/api/projects/project-status");
	}
	@Bean
	public PermissionCheckInterceptor permissionCheckInterceptor(){
		return new PermissionCheckInterceptor(projectMemberService,objectMapper);
	}
}
