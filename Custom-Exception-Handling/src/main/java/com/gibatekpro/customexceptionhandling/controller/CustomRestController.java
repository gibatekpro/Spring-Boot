package com.gibatekpro.customexceptionhandling.controller;

import com.gibatekpro.customexceptionhandling.entity.User;
import com.gibatekpro.customexceptionhandling.exception.CustomException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class CustomRestController {

    Optional<List<User>> users;

    //We are not making use of a database, so we will create
    // the users here.
    @PostConstruct
    public void loadData() {

        List<User> userList = new ArrayList<>();

        userList.add(new User("0", "Tony"));
        userList.add(new User("1", "Frank"));
        userList.add(new User("2", "Jean"));

        users = Optional.of(userList);

    }

    @GetMapping("/getUserById")
    public ResponseEntity<User> getUser(@RequestParam("id") String id, HttpServletRequest request) {

        //Because this is just an example to demonstrate how we can throw our
        //custom extension, we will not include any real Database Applications

        for (User user : users.get()) {

            if (user.getId().equals(id)) {

                return ResponseEntity.ok(user);

            }

        }

        //Here we throw our custom exception, which in our case is the "not found"
        // exception
        throw new CustomException("User not found", request.getRequestURI());

    }

}
