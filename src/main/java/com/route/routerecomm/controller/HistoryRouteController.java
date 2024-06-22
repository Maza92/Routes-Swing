/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.route.routerecomm.controller;

import com.route.routerecomm.model.HistoryRoute;
import com.route.routerecomm.service.HistoryRouteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Luis
 */
@RestController
public class HistoryRouteController {
    
    HistoryRouteService service;

    @Autowired
    public HistoryRouteController(HistoryRouteService service) {
        this.service = service;
    }
    
    @PostMapping("/api/user/{id}/route")
    public HistoryRoute saveRoute(@RequestBody HistoryRoute route, @PathVariable Long id) {
        return service.saveRoute(route, id);
    } 
    
    @GetMapping("api/user/{id}/routes")
    public List<HistoryRoute> getUserRoutes(@PathVariable Long id) {
        return service.getRoutes(id);
    }
}
