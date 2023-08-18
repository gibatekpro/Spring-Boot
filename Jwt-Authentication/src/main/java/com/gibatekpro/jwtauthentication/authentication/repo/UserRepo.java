package com.gibatekpro.jwtauthentication.authentication.repo;

import com.gibatekpro.jwtauthentication.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface UserRepo extends JpaRepository<User, Long> {



}
