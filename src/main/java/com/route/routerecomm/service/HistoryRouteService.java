/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.route.routerecomm.service;

import com.route.routerecomm.model.HistoryRoute;
import com.route.routerecomm.model.user.User;
import com.route.routerecomm.repository.HistoryRouteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Luis
 */
@Service
public class HistoryRouteService {

    HistoryRouteRepository repository;

    UserService userService;

    @Autowired
    public HistoryRouteService(HistoryRouteRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public HistoryRoute saveRoute(HistoryRoute route, Long userId) {

        route.setUser(
            userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId))
        );
        return repository.save(route);
    }
    
    public List<HistoryRoute> getRoutes(Long userId) {
        return repository.findByUserId(userId);
    }

}
