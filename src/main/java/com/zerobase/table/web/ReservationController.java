package com.zerobase.table.web;

import com.zerobase.table.entity.ReservationEntity;
import com.zerobase.table.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/reservations")

public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/{restaurantId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> makeReservation(@PathVariable Long restaurantId,
                                             @RequestBody ReservationEntity reservation){
        //예약 API
        ReservationEntity createdReservation = reservationService.makeReservation(restaurantId, reservation);
        return ResponseEntity.ok(createdReservation);
    }

    @PutMapping("/visit/{reservationId}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> checkVisited(@PathVariable Long reservationId) {
        //방문확인 API
        ReservationEntity newReservation = reservationService.CheckVisited(reservationId);
        return ResponseEntity.ok(newReservation);
    }

    @PostMapping("/approve/{reservationId}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> approveReservation(@PathVariable Long reservationId) {
        //예약승인 API
        ReservationEntity approvedReservation = reservationService.approveReservation(reservationId);
        return ResponseEntity.ok(approvedReservation);
    }


    @PostMapping("/reject/{reservationId}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> rejectReservation(@PathVariable Long reservationId) {
        //예약거절 API
        ReservationEntity rejectedReservation = reservationService.rejectReservation(reservationId);
        return ResponseEntity.ok(rejectedReservation);
    }
}
