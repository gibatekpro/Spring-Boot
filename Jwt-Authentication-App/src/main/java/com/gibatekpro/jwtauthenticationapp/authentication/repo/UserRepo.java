package com.gibatekpro.jwtauthenticationapp.authentication.repo;

import com.gibatekpro.jwtauthenticationapp.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

}
