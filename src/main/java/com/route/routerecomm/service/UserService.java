/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.route.routerecomm.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.route.routerecomm.model.user.User;
import com.route.routerecomm.model.user.UserCredentialDto;
import com.route.routerecomm.model.user.UserVerificationResponseDto;
import com.route.routerecomm.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Luis
 */
@Service
public class UserService {

    UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(User user) {

        String bcryptHashPassword = BCrypt
                .withDefaults()
                .hashToString(4, user
                        .getPassword()
                        .toCharArray());
        
        user.setPassword(bcryptHashPassword);

        return repository.save(user);
    }
    
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public UserVerificationResponseDto verifyUser(UserCredentialDto credential) {

        List<Object[]> results = repository.getUserByEmail(credential.getEmail());

        if (results.isEmpty()) {
            return new UserVerificationResponseDto(null, null, credential.getEmail(), false);
        }

        Object[] result = results.get(0);

        String storedPassword = (String) result[3];

        BCrypt.Result passResult = BCrypt
                .verifyer()
                .verify(credential
                        .getPassword()
                        .toCharArray(), storedPassword);

        return new UserVerificationResponseDto( (Long) result[0], (String) result[1], credential.getEmail(), passResult.verified);
    }

}
