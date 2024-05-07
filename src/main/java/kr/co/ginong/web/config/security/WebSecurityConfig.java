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
//	public UserDetailsService jdbcUserDetailsService() {
//
////      -> 결과 집합의 모양
////         ┌────────────┬───────────┬─────────┐
////         │  username  │  password │ enabled │
////         ├────────────┼───────────┼─────────┤
////         │   newlec   │    111    │    1    │
//
//		String userSql = "select USER_NAME as username, pwd AS password, 1 AS enabled from `MEMBER` where USER_NAME=?";
//
////			┌────────────┬───────────┬
//////        │  username  │ authority │
//////         ├────────────┼───────────┼
//////         │   newlec   │    111    │
//		String authorSql = """
//				SELECT
//					a.USER_NAME as username
//					, b.role_name  AS authority
//				FROM `MEMBER` a
//				RIGHT JOIN MEMBER_ROLE b
//				ON a.ID = b.MEMBER_ID
//				WHERE a.USER_NAME = ?;
//				""";
//
//		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);        //dataSource(데이터베이스 접속 정보)를 가진 JdbcUserDetailsManager 객체 생성
//		manager.setUsersByUsernameQuery(userSql);                                    //유저 정보 쿼리 JdbcUserDetailsManager 에 넣기
//		manager.setAuthoritiesByUsernameQuery(authorSql);                            //인증 정보 쿼리 JdbcUserDetailsManager 에 넣기
//
//
//		return manager;
//	}

