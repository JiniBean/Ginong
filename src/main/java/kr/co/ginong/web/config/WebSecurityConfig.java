package kr.co.ginong.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
		.csrf((csrf) -> csrf.disable())
		.authorizeHttpRequests((requests) -> requests
		.anyRequest().permitAll()
		);
		

		
		return http.build();
	}
}
