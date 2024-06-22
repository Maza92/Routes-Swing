
package com.route.routerecomm.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.route.routerecomm.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author luis
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double latitud;
    private Double longitud;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
