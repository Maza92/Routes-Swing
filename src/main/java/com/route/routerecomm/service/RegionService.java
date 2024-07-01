/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.route.routerecomm.service;

import com.route.routerecomm.model.Region;
import com.route.routerecomm.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Luis
 */
@Service
public class RegionService {
    
    RegionRepository repository;
    
    @Autowired
    public RegionService(RegionRepository repository) {
        this.repository = repository;
    }
    
    public Region SaveRegion(Region region) {
        return repository.save(region);
    }
}
