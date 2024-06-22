package com.route.routerecomm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.route.routerecomm.model.Horary;
import com.route.routerecomm.service.HoraryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class HoraryController {
    HoraryService service;

    @Autowired
    public HoraryController(HoraryService service) {
        this.service = service;
    }

    @GetMapping("/api/horary/route/{id}")
    public List<Horary> getHoraries(@PathVariable Long id) {
        return service.getoHorary(id);
    }

    
}
