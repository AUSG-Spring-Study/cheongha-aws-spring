package org.example.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
// Spring Security 설정들을 활성화
@EnableWebSecurity
// 시큐리티 관련 클래스
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserServcie;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                // h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    // URL별 권한 관리를 설정하는 옵션의 시작점
                    // authorizeRequests가 선언되어야만 andMatchers 옵션 사용가능
                    .authorizeRequests()
                    // 권한 관리 대상을 지정하는 옵션
                    // URL, HTTP 메소드별로 관리가 가능
                    // "/" 등 지정된 URL들은 permitAll() 옵션을 통해 전체 연람 권함을 줌
                    // "/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록
                    .antMatchers("/", "/css/**", "/images/**",
                            "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    // 설정된 값들 이외 나머지 URL들
                    // authenticated()을 추가하여 나머지 URL들은 모두 인증된 사용자들에게만 허용하게 한다
                    // 인증된 사용자 즉, 로그인한 사용자들
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                    .oauth2Login()
                        // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정 담당
                        .userInfoEndpoint()
                            // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체 등록
                            // 리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시
                            .userService(customOAuth2UserServcie);
    }
}
