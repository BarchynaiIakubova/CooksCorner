package CooksCorner.CooksCorner.repositories;

import CooksCorner.CooksCorner.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {


}
