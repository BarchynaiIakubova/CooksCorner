package CooksCorner.CooksCorner.repositories;

import CooksCorner.CooksCorner.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("select i from Photo i where i.link = :link")
    Optional<Photo> findByLink(String link);
}
