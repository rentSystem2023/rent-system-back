package com.rentcar.back.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rentcar.back.entity.UserEntity;
import com.rentcar.back.provider.JwtProvider;
import com.rentcar.back.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

// Spring Security Filter Chain에 추가할 JWT 필터 
// - Request 객체로부터 header 정보를 받아와서 Header에 있는 Authorized 필드의 Bearer 토큰 값을 가져와서 JWT 검증
// - 접근 주체의 권한을 확인하여 권한 등록 추가

// 커스텀 생성 하려면 OncePerRequestFilter으로 클래스를 확장해줘야함 
// JwtAuthenticationFilter를 필수로 해야되는 오버라이딩 자동 작업 클릭
@Component
@RequiredArgsConstructor // 생성자로 의존성 주입
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // 토큰 지정
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository; //  권한을 확인하기 위해 작업

    // * 아래의 코드는 JwtAuthenticationFilter의 실제 동작
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    // * 여기까지는 외울 필요 없음.
    
        try {

            // Request 객체에서 Bearer 토큰 값 가져오기
            // token에 올 수 있는 데이터로는 token , null 이 올 수 있기에 null 이 왔을땐 동작을 하지 못하게 if문으로 작업함
            String token = parseBearerToken(request);
            if (token== null){
                filterChain.doFilter(request, response);
                return;
            }

            // JWT 검증
            String userId = jwtProvider.validate(token);
            // null을 받는 상태라면 필터체인으로 넘김
            if (userId == null){
                filterChain.doFilter(request, response);
                return;
            }

            // 위에서 검증된 결과의 접근 주체에 어떠한 권한이 있는지를 확인 (권한이 데이터베이스에 있음) 
            // 리포지토리를 거쳐 접근 권한을 가져와야 함 
            UserEntity userEntity = userRepository.findByUserId(userId);
            // Null 에 대한 상황 처리를 해주면 좋음
            if (userEntity == null){
                filterChain.doFilter(request, response);
                return;
            }
            String role = userEntity.getUserRole();

            // 권한 테이블(리스트)을 생성
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));

            // Context에 접근 주체 정보를 추가하는 작업


            //토큰을 먼저 만들기
            AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, null,authorities);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            
            // 빈공간 만들기
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authenticationToken);

            //이 보안 컨텍스트에는 인증된 사용자 및 해당 사용자의 권한 등의 정보가 포함
            SecurityContextHolder.setContext(securityContext);

        } catch (Exception exception) {
            // 예외 발생 시
            exception.printStackTrace();
        }
            // 이 작업을 해줘야 동작함 (리턴과 같은 역활을 함)
        filterChain.doFilter(request, response);
    }

    // Request 객체에서 Bearer 토큰 값을 가져오는 메서드 생성
    private String parseBearerToken(HttpServletRequest request) {

        // request 객체에서 header 에서 Authorization 필드 값 추출
        String authorization = request.getHeader("Authorization");

        // Authorization 필드값 존재 여부 확인
        // 존재하지 않는다면 null 반환
        boolean hasAuthorization = StringUtils.hasText(authorization);
        if (!hasAuthorization)
            return null;

        // barrer 인증 여부 확인 / 띄어쓰기 있으니 확인해야 함
        // 존재하지 않는다면 null 반환
        boolean isBearer = authorization.startsWith("Bearer ");
        if (!isBearer)
            return null;

        // Authorization 필드 값에서 토큰 추출
        // 뒤에서 7자리까지 토큰 값을 가져옴
        String token = authorization.substring(7);
        return token;

    }

}
