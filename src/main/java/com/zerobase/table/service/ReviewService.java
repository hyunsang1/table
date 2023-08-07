package com.zerobase.table.service;

import com.zerobase.table.entity.ReservationEntity;
import com.zerobase.table.entity.RestaurantEntity;
import com.zerobase.table.repository.ReservationRepository;
import com.zerobase.table.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReservationRepository reservationRepository;
    private final RestaurantRepository restaurantRepository;

    public RestaurantEntity writeReview(Long reservationId, String review) {
        // 예약 id와 리뷰내용을 입력받아 방문 여부가 true 일 경우, 리뷰내용을 저장
        // false일 경우, 오류 메시지 반환
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("예약 정보를 찾을 수 없습니다 : " + reservationId));

        if (!reservation.isVisited()){
            throw new IllegalStateException("방문을 하지않아 리뷰를 작성 할 수 없습니다.");
        }

        RestaurantEntity restaurant = reservation.getRestaurante();
        restaurant.setReview(review);

        return restaurantRepository.save(restaurant);

    }
}
