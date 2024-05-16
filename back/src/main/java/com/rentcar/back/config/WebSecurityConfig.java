package com.rentcar.back.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.rentcar.back.filter.JwtAuthenticationFilter;
import com.rentcar.back.handler.OAuth2SuccessHandler;
import com.rentcar.back.service.implementation.OAuth2UserServiceImplementation;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

//  Spring Web Security 설정
// - Basic 인증 미사용 으로 변경
// - CSRF 정책 미사용
// - Session 생성 정책 미사용
// - CORS 정책 (모든 출처 - 모든 메서드 - 모든 패턴 허용)
// - JwtAuthenticationFilter 추가 ( UsernamePasswordAuthenticationFilter 이전에 추가 )  
@Configurable
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        private final OAuth2UserServiceImplementation oAuth2UserService;
        private final OAuth2SuccessHandler oAuth2SuccessHandler;

        @Bean
        protected SecurityFilterChain configure(HttpSecurity httpSecurity)
                        throws Exception {
                httpSecurity
                                .httpBasic(HttpBasicConfigurer::disable) // Basic 미사용
                                .csrf(CsrfConfigurer::disable) // CSRF 미사용
                                .sessionManagement(sessionManagement -> sessionManagement // 받아온 sessionManagement
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .cors(cors -> cors
                                                .configurationSource(corsConfigurationSource()))
                                                .authorizeHttpRequests(request -> request.requestMatchers("/","/api/rentcar/auth/**","/oauth2/callback/*").permitAll()// auth /이후로는 인증 없이 사용 가능/
                                                .requestMatchers("/api/rentcar/board/").hasRole("USER") // permitAll 뒤에 관리자에대한 권한 지정
                                                .requestMatchers("/api/rentcar/board/*/comment").hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                .oauth2Login(oauth2 -> oauth2 
                                // 카카오 네이버 API 경로 관련
                                .authorizationEndpoint(endpoint -> endpoint.baseUri("/api/v1/auth/oauth2"))
                                // 콜백 주소 지정
                                        .redirectionEndpoint(endpoint -> endpoint.baseUri("/oauth2/callback/*"))
                                        .userInfoEndpoint(endpoint -> endpoint.userService(oAuth2UserService))
                                        .successHandler(oAuth2SuccessHandler)
                                )
                                .exceptionHandling(exception -> exception.authenticationEntryPoint(new AuthorizationFailEntryPoint()))//만약 인증 및 인가 작업에 실패하면 'AF'처리  403
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

                return httpSecurity.build(); // 적용
        }

        // Cors 정책 설정
        // CORS 정책 (모든 출처 - 모든 메서드 - 모든 패턴 허용)

        @Bean
        protected CorsConfigurationSource corsConfigurationSource() {

                CorsConfiguration configuration = new CorsConfiguration();
                configuration.addAllowedOrigin("*"); // * 모든것 허용
                configuration.addAllowedHeader("*");
                configuration.addAllowedMethod("*");

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);

                return source;

        }
}

// authenticationEntryPoint()) 에 대한 인스턴스
class AuthorizationFailEntryPoint implements AuthenticationEntryPoint{

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                        AuthenticationException authException) throws IOException, ServletException {
                        
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("{\"code\" : \"AF\",\"message\":\"Authorization Failed\"}");
        }

}