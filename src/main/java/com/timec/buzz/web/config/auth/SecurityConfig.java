package com.timec.buzz.web.config.auth;


import com.timec.buzz.web.domain.user.Role;
import lombok.RequiredArgsConstructor;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final CustomOAuth2UserService customOAuth2UserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				//h2-console 사용을 위한 설정 (s)
				.csrf().disable().headers().frameOptions().disable()
				//h2-console 사용을 위한 설정 (e)
				.and()
				//URL별 권한 관리 설정하는 옵션의 시작점. antMatcher사용에 필수
				.authorizeRequests()
				.antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/h2/**", "/profile").permitAll()
				.antMatchers("/swagger-resources/**").permitAll()
				.antMatchers("/api/v1/**").hasRole(Role.USER.name())
				//설정값 이외의 나머지 URL + 모두 인증 필요
				.anyRequest().authenticated()
				.and()
				//로그아웃 설정 진입점 + 성공 시 이동
				.logout().logoutSuccessUrl("/")
				.and()
				//OAuth2 로그인 기능 설정의 진입점
				.oauth2Login()
				//OAuth2 로그인 성공 이후 사용자 정보 가져올 때의 설정들을 담당.
				.userInfoEndpoint()
				//소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록.
				//리소스 서버(소셜 로그인 서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
				.userService(customOAuth2UserService);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers("/spring-security-rest/**", "/swagger-ui/**", "/v2/api-docs", "/v3/api-docs", "/configuration/ui",
				"/swagger-resources", "/configuration/security", "/swagger-ui.html", "/swagger-ui/**","/webjars/**", "/swagger/**");
	}
}
