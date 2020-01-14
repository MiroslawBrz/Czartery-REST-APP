package pl.miroslawbrz.czartery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.miroslawbrz.czartery.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserMail(String mail);
}
