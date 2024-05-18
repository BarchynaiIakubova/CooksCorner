package CooksCorner.CooksCorner.repositories;

import CooksCorner.CooksCorner.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {


}
