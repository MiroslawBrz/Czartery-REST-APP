package pl.miroslawbrz.czartery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import pl.miroslawbrz.czartery.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
