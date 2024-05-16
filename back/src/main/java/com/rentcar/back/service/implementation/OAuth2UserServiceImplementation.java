package com.rentcar.back.service.implementation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.rentcar.back.common.object.CustomOAuth2User;
import com.rentcar.back.entity.EmailAuthNumberEntity;
import com.rentcar.back.entity.UserEntity;
import com.rentcar.back.repository.EmailAuthNumberRepository;
import com.rentcar.back.repository.UserRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImplementation extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final EmailAuthNumberRepository emailAuthNumberRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    // 유저정보를 userRequest에 담기
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // oAuth2User 객체 만들기
        OAuth2User oAuth2User = super.loadUser(userRequest);
        // 네이버인지 카카오인지 확인
        String oauthClientName = userRequest.getClientRegistration().getClientName().toUpperCase();

        // System.out.println(oauthClientName);

        // try{
        // System.out.println(new
        // ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
        // }catch (Exception exception){
        // exception.printStackTrace();
        // }
            // id를 받고 user id형태로 받아주기 위한 작업
            String id = getId(oAuth2User, oauthClientName);
            // 10자리수로 검증 진행
            String userId = oauthClientName + "_"+id.substring(0,10);

            // 데이터베이스에 존재하는지 검사하는 코드
            boolean isExistUser = userRepository.existsByUserId(userId);
            if (!isExistUser){
                //*  임시 작업 (실제로는 더 많은 구현이 필요함)
                String email = id + "@" + oauthClientName.toLowerCase() + ".com";
                String password = passwordEncoder.encode(id);
                
            EmailAuthNumberEntity emailAuthNumberEntity = new EmailAuthNumberEntity(email, "0000");
            emailAuthNumberRepository.save(emailAuthNumberEntity);
            
                // 가입권한과 , 경로는 만들어져 있어서 바로 사용 가능
                UserEntity userEntity = new UserEntity(userId,password,email,"ROLE_USER",oauthClientName);    
                userRepository.save(userEntity);
            }

        return new CustomOAuth2User(userId,oAuth2User.getAttributes());
    }

    private String getId(OAuth2User oAuth2User, String oauthClientName) {

        String id = null;

        if (oauthClientName.equals("KAKAO")) {
            Long longId = (Long) oAuth2User.getAttributes().get("id");
            id = longId.toString();
        }
        if (oauthClientName.equals("NAVER")) {
            Map<String, String> response = (Map<String, String>) oAuth2User.getAttributes().get("response");
            id = response.get("id");
        }
        return id;
    }

}