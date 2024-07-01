/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.route.routerecomm.controller;

import com.route.routerecomm.model.Region;
import com.route.routerecomm.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Luis
 */
@RestController
public class RegionController {
    
    RegionService service;
    
    @Autowired
    public RegionController(RegionService service) {
        this.service = service;
    }
    
    @PostMapping("/api/region/save")
    public Region save(@RequestBody Region region) {
        return service.SaveRegion(region);
    }
}
