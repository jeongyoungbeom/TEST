package com.young.springboot.config.auth;

import com.young.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
// spring security설정들을 활성화 시켜줍니다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                // h2-console 화면을 사용하기 위해 해당 옵션들을 disable 합니다.
                .and()
                .authorizeRequests()
                /*
                    URL별 권한 관리를 설정하는 옵션의 시작점입니다.
                    authorizeRequests가 선언되어야만 antMathchers 옵션을 사용할 수 있습니다.
                 */
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                /*
                    권한 관리 대상을 지정하는 옵션입니다.
                    URL, HTTP 메소드별로 관리가 가능합니다.
                    "/"등 지정된 URL들은 permitAll()옵션을 통해 전체 열람권한을 주었습니다.
                    "/api/v1/**" 주소를 가진 API는 USER권한을 가진 사람만 가능하도록 했습니다.
                 */
                .anyRequest().authenticated()
                /*
                    설정된 값들 이외 나머지 URL들을 나타냅니다.
                    authenticated를 추가하여 나머지 URL들은 모두 인증된 사용자들에게만 허용하게 합니다.
                    인증된 사용자 즉, 로그인한 사용자들을 이야기합니다.
                 */
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}
