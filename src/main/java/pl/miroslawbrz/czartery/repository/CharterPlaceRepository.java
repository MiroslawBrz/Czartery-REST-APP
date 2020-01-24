package pl.miroslawbrz.czartery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import pl.miroslawbrz.czartery.model.CharterPlace;

@Repository
public interface CharterPlaceRepository extends JpaRepository<CharterPlace, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE public.charter_place SET user_id = ?1 WHERE charter_place_id=?2", nativeQuery = true)
    void setRelationWithUser(Long userId, Long charterPlaceId);
}
