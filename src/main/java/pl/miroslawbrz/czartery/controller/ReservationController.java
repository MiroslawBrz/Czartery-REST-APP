package pl.miroslawbrz.czartery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.miroslawbrz.czartery.api.request.CreateReservationRequest;
import pl.miroslawbrz.czartery.api.response.ReservationResponse;
import pl.miroslawbrz.czartery.model.ReservationDetails;
import pl.miroslawbrz.czartery.service.ReservationService;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDetails>> getAllReservations(){
       return reservationService.getAllReservations();
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<ReservationDetails> getSingleReservation(@PathVariable Long id){
        return reservationService.getReservationDetails(id);
    }

    @PostMapping("/{yachtId}/reservations")
    public ResponseEntity<ReservationResponse> addNewReservation(
            @PathVariable Long yachtId,
            @RequestBody CreateReservationRequest request){

         return reservationService.addReservation(request, yachtId);
    }

    @PutMapping("/reservations/{resId}")
    public ResponseEntity<ReservationResponse> updateReservation(
            @PathVariable Long resId,
            @RequestBody CreateReservationRequest request
    ){
        return reservationService.changeReservation(resId, request);
    }

    @DeleteMapping("reservations/{id}")
    public ResponseEntity<ReservationResponse> deleteReservation(@PathVariable Long id){
        return reservationService.deleteReservation(id);
    }

}
