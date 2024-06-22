/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.route.routerecomm.service;

import com.route.routerecomm.model.HistoryRegion;
import com.route.routerecomm.repository.HistoryRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Luis
 */
@Service
public class HistoryRegionService {
    
    HistoryRegionRepository repository;
    
    @Autowired
    public HistoryRegionService(HistoryRegionRepository repository) {
        this.repository = repository;
    }
    
    public HistoryRegion saveRegion(HistoryRegion region) {
        return repository.save(region);
    }
}
