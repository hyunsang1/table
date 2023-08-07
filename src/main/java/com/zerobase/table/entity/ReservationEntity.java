package com.zerobase.table.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "RESERVATION")
@Getter
@Setter
@NoArgsConstructor
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurante;

    private String customerName;

    private boolean visited = false;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.PENDING;


}
