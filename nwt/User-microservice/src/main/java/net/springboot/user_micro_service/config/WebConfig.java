package net.springboot.user_micro_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import shuaicj.example.security.common.JwtAuthenticationConfig;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	LoggerInterceptor inter;
	
	@Bean
    public LoggerInterceptor logger() {
        return new LoggerInterceptor();
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(inter);
	}
}