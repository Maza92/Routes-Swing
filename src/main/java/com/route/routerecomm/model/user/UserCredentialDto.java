/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.route.routerecomm.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Luis
 */
@Getter
@Setter
@NoArgsConstructor
public class UserCredentialDto {
    private String email;
    private String password;
    
    public UserCredentialDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
}
