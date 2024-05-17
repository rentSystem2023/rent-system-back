package com.rentcar.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.rentcar.back.entity.UserEntity;

import lombok.Getter;

@Getter
public class UserListItem {
    private String userId;
    private String nickName;
    private String userEmail;
    private String joinDate;

    private UserListItem(UserEntity userEntity) throws Exception {
        
        this.userId = userEntity.getUserId();
        this.nickName = userEntity.getNickName();
        this.userEmail = userEntity.getUserEmail();
        this.joinDate = userEntity.getJoinDate();
    }

    public static List<UserListItem> getList (List<UserEntity> userEntities) throws Exception {
        List<UserListItem> userList = new ArrayList<>();

        for (UserEntity userEntity: userEntities) {
            UserListItem userListItem = new UserListItem(userEntity);
            userList.add(userListItem);
        }
        return userList;
    
    }

}
