package com.zerobase.table.entity;

import com.zerobase.table.model.Restaurant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "RESTAURANT")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private String description;

    private String review;

    public RestaurantEntity(Restaurant restaurant) {
        this.name = restaurant.getName();
        this.location = restaurant.getLocation();
        this.description = restaurant.getDescription();
    }

}
