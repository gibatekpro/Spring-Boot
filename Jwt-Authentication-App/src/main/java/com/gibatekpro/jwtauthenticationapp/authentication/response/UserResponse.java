package com.gibatekpro.jwtauthenticationapp.authentication.response;

import com.gibatekpro.jwtauthenticationapp.authentication.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Setter
@Getter
public class UserResponse {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String jwtToken;

    private Collection<Role> roles;


}
