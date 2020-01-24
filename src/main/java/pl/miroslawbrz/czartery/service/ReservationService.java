package pl.miroslawbrz.czartery.service;

import org.springframework.http.ResponseEntity;
import pl.miroslawbrz.czartery.api.request.CreateReservationRequest;
import pl.miroslawbrz.czartery.api.response.ReservationResponse;
import pl.miroslawbrz.czartery.model.ReservationDetails;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    ResponseEntity<List<LocalDate>> getAllReservedDays(Long yachtId);

    ResponseEntity<ReservationResponse> addReservation();

    ResponseEntity<ReservationResponse> changeReservation();

    ResponseEntity<ReservationDetails> getReservationDetails();

    ResponseEntity<ReservationResponse> deleteReservation();



}
