/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.route.routerecomm.controller;

import com.route.routerecomm.model.Route;
import com.route.routerecomm.service.RouteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Luis
 */
@RestController
public class RouteController {
    
    RouteService service;
    
    @Autowired
    public RouteController(RouteService service) {
        this.service = service;
    }
    
    @GetMapping("/api/routes")
    public List<Route> finAllRoutes() {
        return service.getRoutes();
    }
    
    @PostMapping("/api/routes")
    public Route save(@RequestBody Route route) {
        return service.save(route);
    }
}
