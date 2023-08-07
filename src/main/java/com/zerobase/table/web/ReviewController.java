package com.zerobase.table.web;

import com.zerobase.table.entity.RestaurantEntity;
import com.zerobase.table.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("{reservationId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> writeReview(@PathVariable Long reservationId,
                                         @RequestBody String review) {
        //리뷰 작성 API
        RestaurantEntity newRestaurnat = reviewService.writeReview(reservationId, review);
        return ResponseEntity.ok(newRestaurnat);
    }
}
