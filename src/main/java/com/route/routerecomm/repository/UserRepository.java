/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.route.routerecomm.repository;

import com.route.routerecomm.model.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Luis
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "CALL GetUserByEmail(:userEmail)", nativeQuery = true)
    List<Object[]> getUserByEmail(@Param("userEmail") String userEmail);
}
