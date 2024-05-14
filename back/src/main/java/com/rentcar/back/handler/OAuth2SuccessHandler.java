package com.rentcar.back.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.rentcar.back.common.object.CustomOAuth2User;
import com.rentcar.back.provider.JwtProvider;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    //토큰 생성을 위함
    private final JwtProvider jwtProvider;

    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

                CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();
                String userId = oAuth2User.getName();

                //위에서 토큰을 의존성주입으로 가져온 것을 사용
                String token = jwtProvider.create(userId);

                response.sendRedirect("http://localhost:3000/sns/" + token + "/36000");
    }
}
