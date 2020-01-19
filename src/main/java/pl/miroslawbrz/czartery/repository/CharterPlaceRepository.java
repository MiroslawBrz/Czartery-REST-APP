package pl.miroslawbrz.czartery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.miroslawbrz.czartery.model.CharterPlace;

@Repository
public interface CharterPlaceRepository extends JpaRepository<CharterPlace, Long> {

}
