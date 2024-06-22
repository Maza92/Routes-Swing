package com.route.routerecomm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.route.routerecomm.model.Horary;

@Repository
public interface HoraryRepository extends JpaRepository<Horary, Integer> {

    List<Horary> findByRouteId(Long routeId);
}
