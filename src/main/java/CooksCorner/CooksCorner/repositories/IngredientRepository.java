package CooksCorner.CooksCorner.repositories;

import CooksCorner.CooksCorner.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {


    @Query("""
            select i from Ingredient i
            where i.recipe.id = :recipeId
            """)
    List<Ingredient> findbyRecipeId(Long recipeId);
}
