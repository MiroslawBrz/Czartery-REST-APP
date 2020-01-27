package pl.miroslawbrz.czartery.service;

import org.springframework.http.ResponseEntity;
import pl.miroslawbrz.czartery.api.request.CreateReservationRequest;
import pl.miroslawbrz.czartery.api.response.ReservationResponse;
import pl.miroslawbrz.czartery.model.ReservationDetails;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ReservationService {

    ResponseEntity<List<LocalDate>> getAllReservedDaysOfYacht(Long yachtId);

    ResponseEntity<List<ReservationDetails>> getAllReservations();

    ResponseEntity<ReservationResponse> addReservation(CreateReservationRequest request, Long yachtId);

    ResponseEntity<ReservationResponse> changeReservation(Long reservationId, CreateReservationRequest request);

    ResponseEntity<ReservationDetails> getReservationDetails(Long reservationId);

    ResponseEntity<ReservationResponse> deleteReservation(Long reservationId);


}
