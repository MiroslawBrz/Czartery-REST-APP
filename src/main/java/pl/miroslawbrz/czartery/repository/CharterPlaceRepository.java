package pl.miroslawbrz.czartery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.miroslawbrz.czartery.model.CharterPlace;

@Repository
public interface CharterPlaceRepository extends JpaRepository<CharterPlace, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE charter_place SET user_id=? WHERE charter_place_id=?", nativeQuery = true)
    void insertIntoCharterPlaceUserId(Long UserId, Long charterPlaceId );

}
