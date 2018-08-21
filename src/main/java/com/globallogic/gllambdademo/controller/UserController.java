package com.globallogic.gllambdademo.controller;


import com.globallogic.gllambdademo.dto.User;
import com.globallogic.gllambdademo.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    SpeakerService service;

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = service.findAll();
        if (users != null && !users.isEmpty()) {
         return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<User>(Arrays.asList(
                    new User()
                            .setEmail("hernan.urban@globallogic.com")
                            .setName("Hernan"),
                    new User().setEmail("facundo.munoz@globallogic.com")
                            .setName("Facundo"))), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<User> addUsers(@RequestBody User user) {
        return new ResponseEntity<>(service.create(user), HttpStatus.CREATED);

    }
}
