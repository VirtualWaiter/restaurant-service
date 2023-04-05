package com.samuelfhz.restaurantservice.repository;

import com.samuelfhz.restaurantservice.model.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Tables, Long> {


}
