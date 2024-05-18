package CooksCorner.CooksCorner.repositories;

import CooksCorner.CooksCorner.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<Object> findIdByEmail(String name);
}
