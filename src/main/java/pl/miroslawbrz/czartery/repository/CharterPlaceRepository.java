package pl.miroslawbrz.czartery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.miroslawbrz.czartery.model.CharterPlace;

@Repository
public interface CharterPlaceRepository extends JpaRepository<CharterPlace, Long> {

    @Query(value = "UPDATE public.charter_place SET user_id = ?1 WHERE charter_place_id=?2", nativeQuery = true)
    void setRelationWithUser(Long userId, Long charterPlaceId);
}
