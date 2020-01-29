package pl.miroslawbrz.czartery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.miroslawbrz.czartery.model.ReservationDetails;
import pl.miroslawbrz.czartery.model.Yacht;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationDetails, Long> {

    @Query(value = "SELECT * FROM reservation_details WHERE yacht_id=?", nativeQuery = true)
    List<ReservationDetails> getAllReservationsOfYacht(Long charterPlaceId);

    @Query(value = "SELECT * FROM reservation_details WHERE user_id=?", nativeQuery = true)
    List<ReservationDetails> getAllReservationsOfUser(Long charterPlaceId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE public.reservation_details SET user_id = ?1 WHERE reservation_id=?2", nativeQuery = true)
    void setRelationWithUser(Long userId, Long reservationId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE public.reservation_details SET yacht_id = ?1 WHERE reservation_id=?2", nativeQuery = true)
    void setRelationWithYacht(Long yachtId, Long reservationId);

}
