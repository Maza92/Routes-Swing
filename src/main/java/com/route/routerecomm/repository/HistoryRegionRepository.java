/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.route.routerecomm.repository;

import com.route.routerecomm.model.HistoryRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Luis
 */
@Repository
public interface HistoryRegionRepository extends JpaRepository<HistoryRegion, Long>{
    
}
