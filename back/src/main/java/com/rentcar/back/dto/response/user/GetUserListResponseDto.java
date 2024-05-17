package com.rentcar.back.dto.response.user;

import com.rentcar.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class GetUserListResponseDto extends ResponseDto {
    
    private String userList[];
}
