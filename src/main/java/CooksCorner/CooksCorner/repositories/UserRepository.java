package CooksCorner.CooksCorner.repositories;

import CooksCorner.CooksCorner.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
        from User u
        where u.email = :email
""")
    Optional<User> findByEmail(String email);

    Optional<Object> findIdByEmail(String name);


}
