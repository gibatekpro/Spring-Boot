package com.gibatekpro.jwtauthenticationapp.authentication.dto;

import com.gibatekpro.jwtauthenticationapp.authentication.entity.User;
import com.gibatekpro.jwtauthenticationapp.authentication.response.UserResponse;
import org.springframework.beans.BeanUtils;

public class UserDto {

    public static UserResponse toUserResponse(User user){

        UserResponse userResponse = new UserResponse();

        if (user != null) {

            BeanUtils.copyProperties(user, userResponse);

            return userResponse;

        }
        return null;
    }

    public static User toUser(UserResponse userResponse){

        User user = new User();

        if (userResponse != null) {

            BeanUtils.copyProperties(userResponse, user);

            return user;

        }
        return null;
    }

}
