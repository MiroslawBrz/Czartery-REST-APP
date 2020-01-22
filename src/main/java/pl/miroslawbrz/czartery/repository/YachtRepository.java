package pl.miroslawbrz.czartery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.miroslawbrz.czartery.model.Yacht;

import java.util.List;


@Repository
public interface YachtRepository extends JpaRepository<Yacht, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE yacht SET charter_place_id = ?1 WHERE id = ?2", nativeQuery = true)
    void insertIntoYachtCharterPlaceId(Long charterPlaceId, Long id);

    List<Yacht> getAllByCharterPlace_CharterPlaceId(Long id);

}
