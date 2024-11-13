package gdg.farm_in_palm.config;

import gdg.farm_in_palm.security.service.AuthService;
import gdg.farm_in_palm.security.service.AuthFilter;
import gdg.farm_in_palm.security.service.ExceptionHandlingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthService authService;
    @Value("${jwt.secret}") String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        //.requestMatchers("api/auth/login/**", "api/auth/join/**", "api/auth/refresh", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**").permitAll()
                        .anyRequest().permitAll() // 모든 요청 허용
                )
                .httpBasic(httpBasic -> httpBasic.disable()) // httpBasic 비활성화
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 세션 정책 설정

        http.addFilterBefore(new AuthFilter(authService, secretKey), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new ExceptionHandlingFilter(), AuthFilter.class);

        return http.build();
    }
}
