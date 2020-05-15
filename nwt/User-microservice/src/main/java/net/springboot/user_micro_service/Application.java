package net.springboot.user_micro_service;

import java.util.Arrays;
import java.util.List;

import net.springboot.user_micro_service.model.Role;
import net.springboot.user_micro_service.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;

import net.springboot.user_micro_service.repository.UserRepository;

@EnableDiscoveryClient
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
    public CommandLineRunner demoData(UserRepository repo) {
        return args -> { 
        	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            repo.save(new User("Ned","Ned2","nedimvrtic7@gmail.com",passwordEncoder.encode("NedimVrtic1$"), Arrays.asList(new Role("ROLE_ADMIN"))));
			repo.save(new User("Ned","Ned2","nedimvrtic77@gmail.com",passwordEncoder.encode("NedimVrtic1$"), Arrays.asList(new Role("ROLE_USER"))));
		};
    }
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
				jsonConverter.setObjectMapper(new ObjectMapper());
				jsonConverter.setSupportedMediaTypes(ImmutableList.of(new MediaType("application", "json", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET), new MediaType("text", "javascript", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET)));
			}
		}
		return restTemplate;
	}
}

