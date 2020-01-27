package pl.miroslawbrz.czartery.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
@Data
public class ReservationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    private LocalDate localDate;
    private int reservationLength;
    private boolean paid;

    public static final class Builder{



    }

}
