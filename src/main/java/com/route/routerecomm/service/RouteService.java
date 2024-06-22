/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.route.routerecomm.service;

import com.route.routerecomm.model.Route;
import com.route.routerecomm.repository.RouteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Luis
 */
@Service
public class RouteService {
    RouteRepository repository; 
    
    @Autowired
    public RouteService(RouteRepository repository) {
        this.repository = repository;
    }
    
    public List<Route> getRoutes() {
        return repository.findAll();
    }
    
}
