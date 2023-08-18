package com.gibatekpro.jwtauthenticationapp.authentication.repo;

import com.gibatekpro.jwtauthenticationapp.authentication.dto.UserDto;
import com.gibatekpro.jwtauthenticationapp.authentication.entity.Role;
import com.gibatekpro.jwtauthenticationapp.authentication.entity.User;
import com.gibatekpro.jwtauthenticationapp.authentication.exception.AuthException;
import com.gibatekpro.jwtauthenticationapp.authentication.jwt.JwtService;
import com.gibatekpro.jwtauthenticationapp.authentication.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;

@AllArgsConstructor
@Repository
public class AuthRepoImpl implements AuthRepo{

    private final Logger logger = Logger.getLogger(getClass().getName());

    private static final String TAG = "AuthRepoImpl " + " >>>>  ";


    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public Optional<UserResponse> register(User userRequestBody) {

        if (userRequestBody == null) {
            throw new AuthException("User is null");
        }

        User userBody = new User();
        BeanUtils.copyProperties(userRequestBody, userBody);

        //Save the user in the database
        User savedUser = saveUser(userRequestBody);

        //Authenticate the user
        UserResponse userResponseBody = authenticateUser(userBody);

        //generate jwt token
        userResponseBody.setJwtToken(jwtService.generateToken(savedUser));

        return Optional.of(userResponseBody);
    }

    @Override
    public Optional<UserResponse> authenticate(User userRequestBody) {

        //Authenticate the user
        UserResponse userResponseBody = authenticateUser(userRequestBody);

        //Fetch the authenticated user
        User user = userRepo.findUserByEmail(userRequestBody.getEmail()).get();

        //generate jwt token
        userResponseBody.setJwtToken(jwtService.generateToken(user));

        return Optional.of(userResponseBody);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {

        return userRepo.findUserByEmail(email);
    }

    private User saveUser(User user) {

        //encode the user password
        user.setPass(passwordEncoder.encode(user.getPassword()));

        //Add or create User role
        user.setRoles(addOrCreateUserRole());

        User savedUser;

        try {

            savedUser = userRepo.save(user);

        }catch (Exception e){

            throw new AuthException("Could not save this user");

        }

        return savedUser;

    }

    private UserResponse authenticateUser(User user) {

        UserResponse userResponse = new UserResponse();

        try {

            //authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword()
                    )
            );

            try {

                userResponse = UserDto.toUserResponse(userRepo.findUserByEmail(user.getEmail()).get());

            } catch (Exception e) {
                throw new AuthException("token");
            }
        } catch (Exception e) {
            throw new AuthException("auth");
        }

        return userResponse;

    }


    private Collection<Role> addOrCreateUserRole() {

        Collection<Role> roleList = new ArrayList<>();

        try {

            Collection<Role> dbRoleList = roleRepo.findAll();

            if (dbRoleList.isEmpty()) {

                Role userRole = new Role();
                userRole.setName("ROLE_USER");

                roleRepo.save(userRole);

                roleList.add(roleRepo.findByName("ROLE_USER"));

            } else {

                roleList.add(roleRepo.findByName("ROLE_USER"));

            }
        }catch (Exception e){
            throw new AuthException("Could not create or find roles");
        }

        return roleList;

    }

}
