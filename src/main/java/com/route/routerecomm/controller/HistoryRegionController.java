/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.route.routerecomm.controller;

import com.route.routerecomm.model.HistoryRegion;
import com.route.routerecomm.service.HistoryRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Luis
 */
@RestController
public class HistoryRegionController {
    
    HistoryRegionService service;

    @Autowired
    public HistoryRegionController(HistoryRegionService service) {
        this.service = service;
    }
    
    @PostMapping("/api/user/region")
    public HistoryRegion saveRegion(@RequestBody HistoryRegion region) {
        return service.saveRegion(region);
    }
}
