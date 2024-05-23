package CooksCorner.CooksCorner.repositories;


import CooksCorner.CooksCorner.models.UserLikeRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface UserLikeRecipeRepository extends JpaRepository<UserLikeRecipe, Long> {


    @Query("""
            SELECT COUNT (ulr) from UserLikeRecipe ulr where ulr.recipeId = :recipeId
          """)
    Integer likeRecipe(Long recipeId);

    @Modifying
    @Transactional
    @Query("""
            delete from UserLikeRecipe ulr
            where ulr.userId = :userId and ulr.recipeId = :recipeId
        """)
    void removeLikeRecipe(Long userId, Long recipeId);

    Integer countAllByRecipeId(Long recipeId);
}
