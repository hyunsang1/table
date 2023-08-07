package com.zerobase.table.service;

import com.zerobase.table.entity.ReservationEntity;
import com.zerobase.table.entity.ReservationStatus;
import com.zerobase.table.entity.RestaurantEntity;
import com.zerobase.table.repository.ReservationRepository;
import com.zerobase.table.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberService memberService;

    public ReservationEntity makeReservation(Long restaurantId, ReservationEntity reservation) {
        //식당 id와 예약하는 식당의 정보를 입력받아 예약 정보를 생성
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("레스토랑을 찾을 수 없습니다 : " + restaurantId));

        reservation.setRestaurante(restaurant);


        return reservationRepository.save(reservation);
    }

    public ReservationEntity CheckVisited(Long reservationId) {
        //방문확인을 하면 방문여부를 true로 바뚬
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("예약정보를 찾을 수 없습니다 : " + reservationId));
        reservation.setVisited(true);
        return reservationRepository.save(reservation);
    }

    public ReservationEntity approveReservation(Long reservationId) {
        //관리자가 승인을 할 경우, 예약 대기상태를 승인상태로 바꿈
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("에약정보를 찾을 수 없습니다 : "+ reservationId));
        reservation.setStatus(ReservationStatus.APPROVED);
        return reservationRepository.save(reservation);
    }

    public ReservationEntity rejectReservation(Long reservationId) {
        //관리자가 거절을 할 경우, 예약 대기상태를 거절상태로 바꿈
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("에약정보를 찾을 수 없습니다 : " + reservationId));
        reservation.setStatus(ReservationStatus.REJECTED);
        return reservationRepository.save(reservation);
    }
}
