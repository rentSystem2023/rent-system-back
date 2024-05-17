package com.rentcar.back.dto.response.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.object.UserListItem;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserListResponseDto extends ResponseDto {
    private List<UserListItem> userList;

    private GetUserListResponseDto(List<UserEntity> userEntities) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userList = UserListItem.getList(userEntities);
    }

    public static ResponseEntity<GetUserListResponseDto> success (List<UserEntity> userEntities) throws Exception {
        GetUserListResponseDto responseBody = new GetUserListResponseDto(userEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);

    }
}
