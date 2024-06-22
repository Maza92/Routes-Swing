/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.route.routerecomm.controller;

import com.route.routerecomm.model.user.User;
import com.route.routerecomm.model.user.UserCredentialDto;
import com.route.routerecomm.model.user.UserVerificationResponseDto;
import com.route.routerecomm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Luis
 */
@RestController
public class UserController {
    
    UserService service;
    
    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }
    
    @PostMapping("/api/auth/register")
    ResponseEntity<User> registerUser(@RequestBody User user) {
        User createdUser = service.createUser(user);
        
        if (createdUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    
    @PostMapping("/api/auth/login")
    UserVerificationResponseDto loginUser(@RequestBody UserCredentialDto credential) {
        return service.verifyUser(credential);
    }
}
