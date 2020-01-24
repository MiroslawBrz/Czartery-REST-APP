package pl.miroslawbrz.czartery.api.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateReservationRequest {

    private LocalDate localDate;
    private int reservationLength;
    private Long yachtId;

}
