package pl.miroslawbrz.czartery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.miroslawbrz.czartery.api.request.CreateReservationRequest;
import pl.miroslawbrz.czartery.api.response.ReservationResponse;
import pl.miroslawbrz.czartery.model.ReservationDetails;
import pl.miroslawbrz.czartery.repository.ReservationRepository;
import pl.miroslawbrz.czartery.service.ReservationService;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }



    @Override
    public ResponseEntity<List<LocalDate>> getAllReservedDays(Long yachtId) {
        return null;
    }

    @Override
    public ResponseEntity<ReservationResponse> addReservation() {
        return null;
    }

    @Override
    public ResponseEntity<ReservationResponse> changeReservation() {
        return null;
    }

    @Override
    public ResponseEntity<ReservationDetails> getReservationDetails() {
        return null;
    }

    @Override
    public ResponseEntity<ReservationResponse> deleteReservation() {
        return null;
    }

    private void checkIfBookingWithinThisPeriodIsPossible(CreateReservationRequest request){

    }

    private void checkIfBookingHasCorrectDate(CreateReservationRequest request){

    }

    private void checkIfYachtExist(CreateReservationRequest request){

    }


}
