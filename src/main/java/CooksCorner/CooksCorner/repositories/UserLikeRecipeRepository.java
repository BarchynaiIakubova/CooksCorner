package CooksCorner.CooksCorner.repositories;

import CooksCorner.CooksCorner.models.UserLikeRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikeRecipeRepository extends JpaRepository<UserLikeRecipe, Long> {

}
