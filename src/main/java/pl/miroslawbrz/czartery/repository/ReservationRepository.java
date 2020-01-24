package pl.miroslawbrz.czartery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.miroslawbrz.czartery.model.ReservationDetails;
import pl.miroslawbrz.czartery.model.Yacht;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationDetails, Long> {

    @Query(value = "SELECT * FROM reservation_details WHERE yacht_id=?", nativeQuery = true)
    List<ReservationDetails> getAllReservationsOfYacht(Long charterPlaceId);

    @Query(value = "SELECT * FROM reservation_details WHERE user_id=?", nativeQuery = true)
    List<ReservationDetails> getAllReservationsOfUser(Long charterPlaceId);

}
