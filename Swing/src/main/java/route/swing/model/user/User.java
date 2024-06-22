/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package route.swing.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class User {
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    
    private String name;
    
    private String email;

    private String password;

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "name: " + getName()
                + "\nemail: " + getEmail()
                + "\npassword: " + getPassword()
                + "\n";
    }
    
}
