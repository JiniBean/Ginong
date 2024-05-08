package kr.co.ginong.web.config.security;

import kr.co.ginong.web.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private MemberRepository memberRepository;

	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder;
	}
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
		.csrf((csrf) -> csrf.disable())
		.authorizeHttpRequests((requests) -> requests
		.requestMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().permitAll())
		.formLogin((form)->form
				.loginPage("/signin")
				.permitAll())
		.logout((logout)->logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/product/list")
				.permitAll());

		return http.build();
	}
}

