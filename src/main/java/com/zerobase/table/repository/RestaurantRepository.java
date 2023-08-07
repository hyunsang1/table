package com.zerobase.table.repository;

import com.zerobase.table.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long>{
    List<RestaurantEntity> findByNameContainingIgnoreCase(String name);
}
