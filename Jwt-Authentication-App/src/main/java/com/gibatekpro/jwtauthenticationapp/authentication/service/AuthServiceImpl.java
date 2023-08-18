package com.gibatekpro.jwtauthenticationapp.authentication.service;

import com.gibatekpro.jwtauthenticationapp.authentication.entity.User;
import com.gibatekpro.jwtauthenticationapp.authentication.repo.AuthRepo;
import com.gibatekpro.jwtauthenticationapp.authentication.repo.UserRepo;
import com.gibatekpro.jwtauthenticationapp.authentication.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthRepo authRepo;

    @Override
    public Optional<UserResponse> register(User user) {
        return authRepo.register(user);
    }

    @Override
    public Optional<UserResponse> authenticate(User user) {
        return authRepo.authenticate(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return authRepo.findUserByEmail(email);
    }
}
