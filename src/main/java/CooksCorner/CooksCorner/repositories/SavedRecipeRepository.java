package CooksCorner.CooksCorner.repositories;

import CooksCorner.CooksCorner.models.SavedRecipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SavedRecipeRepository extends JpaRepository<SavedRecipes, Long> {

    @Query("""
            SELECT COUNT (sr) from SavedRecipes sr where sr.recipeId = :recipeId
          """)
    Integer saveRecipe(Long recipeId);

    @Modifying
    @Transactional
    @Query("""
            delete from SavedRecipes sr
            where sr.userId = :userId and sr.recipeId = :recipeId
        """)
    void removeSavedRecipe(Long userId, Long recipeId);
}
