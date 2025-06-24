package kr.mywork.common.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.mywork.common.api.components.filter.HttpLoggingFilter;
import kr.mywork.common.api.components.filter.RequestTrackingIdFilter;

@Configuration
public class LoggingFilterConfig {

	@Bean
	public RequestTrackingIdFilter requestTrackingIdFilter(){
		return new RequestTrackingIdFilter();
	}

	@Bean
	public HttpLoggingFilter httpLoggingFilter(){
		return new HttpLoggingFilter();
	}

}
