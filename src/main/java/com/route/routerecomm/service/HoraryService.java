package com.route.routerecomm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.route.routerecomm.model.Horary;
import com.route.routerecomm.repository.HoraryRepository;

@Service
public class HoraryService {

    HoraryRepository repository;

    @Autowired
    public HoraryService(HoraryRepository repository) {
        this.repository = repository;
    }

    public List<Horary> getoHorary(Long id) {
        return repository.findByRouteId(id);
    }
}
