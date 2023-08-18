package com.gibatekpro.jwtauthenticationapp.authentication.repo;

import com.gibatekpro.jwtauthenticationapp.authentication.entity.User;
import com.gibatekpro.jwtauthenticationapp.authentication.response.UserResponse;

import java.util.Optional;

public interface AuthRepo {

    Optional<UserResponse> register(User user);

    Optional<UserResponse> authenticate(User user);

    Optional<User> findUserByEmail(String email);

}
