package de.lonifa.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	private final HeaderInterceptor headerInterceptor;

	public WebMvcConfig(HeaderInterceptor headerInterceptor) {
		this.headerInterceptor = headerInterceptor;
	}

	@Override
	public void addInterceptors(@NonNull InterceptorRegistry registry) {
		if(headerInterceptor != null){
			registry.addInterceptor(headerInterceptor);
		}
	}
}
