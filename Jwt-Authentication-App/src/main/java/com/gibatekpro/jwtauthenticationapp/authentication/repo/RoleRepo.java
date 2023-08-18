package com.gibatekpro.jwtauthenticationapp.authentication.repo;

import com.gibatekpro.jwtauthenticationapp.authentication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
