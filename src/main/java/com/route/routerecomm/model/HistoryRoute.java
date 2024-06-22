/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.route.routerecomm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.route.routerecomm.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Entity
public class HistoryRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    
    @OneToOne
    @JoinColumn(name = "start_id", unique = false)
    private HistoryRegion start;
    
    @OneToOne
    @JoinColumn(name = "end_id", unique = false)
    private HistoryRegion end;
}
