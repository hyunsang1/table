package com.zerobase.table.web;

import com.zerobase.table.entity.RestaurantEntity;
import com.zerobase.table.model.Restaurant;
import com.zerobase.table.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    @PostMapping("/regist")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> addRestaurant(@RequestBody Restaurant request) {
        //식당 추가 API
        try {
            RestaurantEntity restaurantEntity = new RestaurantEntity(request);
            RestaurantEntity savedRestaurant = restaurantRepository.save(restaurantEntity);
            return ResponseEntity.ok(savedRestaurant);
        } catch (Exception e) {
            log.error("식당 정보 저장 실패 : ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getRestaurantByName(@PathVariable String name) {
        //식당 검색 API
        List<RestaurantEntity> restaurants = restaurantRepository.findByNameContainingIgnoreCase(name);
        if(restaurants.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(restaurants);
    }

}
