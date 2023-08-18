package com.gibatekpro.jwtauthenticationapp.authentication.service;

import com.gibatekpro.jwtauthenticationapp.authentication.entity.User;
import com.gibatekpro.jwtauthenticationapp.authentication.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AuthService {

    Optional<UserResponse> register(User user);

    Optional<UserResponse> authenticate(User user);

    Optional<User> findUserByEmail(String email);

}
