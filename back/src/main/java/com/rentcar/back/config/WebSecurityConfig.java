package com.rentcar.back.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

@Configurable
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        private final OAuth2UserServiceImplementation oAuth2UserService;
        private final OAuth2SuccessHandler oAuth2SuccessHandler;

        @Bean
        protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
                httpSecurity
                                .httpBasic(HttpBasicConfigurer::disable)
                                .csrf(CsrfConfigurer::disable)
                                .sessionManagement(sessionManagement -> sessionManagement
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                                .authorizeHttpRequests(request -> request
                                                // 모두 접근 가능
                                                .requestMatchers("/", "/api/rentcar/auth/**",
                                                                "/file/**", "/api/rentcar/auth/find-id/*",
                                                                "/api/rentcar/auth/find-password/*",
                                                                "/api/rentcar/auth/find-password/**",
                                                                "/oauth2/callback/*",
                                                                "/api/rentcar/notice/list/**",
                                                                "/api/rentcar/qna/list/**",
                                                                "/api/rentcar/*/*/increase-view-count",
                                                                "/api/rentcar/reservation/mylist",
                                                                "/api/rentcar/reservation/search",
                                                                "/api/rentcar/reservation/popular",
                                                                "/api/rentcar/reservation/search/**",
                                                                "/upload").permitAll()
                                                // 사용자만 접근 가능(USER)
                                                .requestMatchers("/api/rentcar/qna/regist",
                                                                "/api/rentcar/qna/*/modify",
                                                                "/api/rentcar/qna/*/delete",
                                                                "/api/rentcar/user/information/*",
                                                                "/api/rentcar/qna/mylist",
                                                                "/api/rentcar/reservation/regist").hasRole("USER")
                                                .requestMatchers(HttpMethod.GET, "/api/rentcar/user/information/").authenticated()
                                                // 괸라자만 접근 가능(ADMIN)
                                                .requestMatchers("/api/rentcar/qna/*/comment",
                                                                "/api/rentcar/notice/regist",
                                                                "/api/rentcar/notice/*/modify",
                                                                "/api/rentcar/notice/*/delete",
                                                                "/api/rentcar/company/**", "/api/rentcar/admin/list/**",
                                                                "/api/rentcar/reservation/cancel/**",
                                                                "/api/rentcar/reservation/list/**",
                                                                "/api/rentcar/reservation/*",
                                                                "/api/rentcar/reservation/*/approve").hasRole("ADMIN")
                                                .anyRequest().authenticated())

                                .oauth2Login(oauth2 -> oauth2
                                                .authorizationEndpoint(endpoint -> endpoint
                                                                .baseUri("/api/rentcar/auth/oauth2"))
                                                .redirectionEndpoint(endpoint -> endpoint.baseUri("/oauth2/callback/*"))
                                                .userInfoEndpoint(endpoint -> endpoint.userService(oAuth2UserService))
                                                .successHandler(oAuth2SuccessHandler))
                                .exceptionHandling(exception -> exception
                                                .authenticationEntryPoint(new AuthorizationFailedEntryPoint()))
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

                return httpSecurity.build();
        }

        @Bean
        protected CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.addAllowedOrigin("*");
                configuration.addAllowedHeader("*");
                configuration.addAllowedMethod("*");

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);

                return source;
        }

}

class AuthorizationFailedEntryPoint implements AuthenticationEntryPoint {

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                        AuthenticationException authException) throws IOException, ServletException {

                authException.printStackTrace();

                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("{ \"code\":\"AF\",\"message\":\"Authorization Failed\" }");

        }
        
        // CORS 설정을 위한 Bean 등록
        @Bean
        protected CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.addAllowedOrigin("*"); 
                configuration.addAllowedHeader("*");
                configuration.addAllowedMethod("*");

                // Kakao Maps API 도메인 추가
                configuration.addAllowedOrigin("https://dapi.kakao.com");

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);

                return source;
        }
}
