package CooksCorner.CooksCorner.repositories;

import CooksCorner.CooksCorner.dto.responses.RecipeResponseByCategory;
import CooksCorner.CooksCorner.dto.responses.RecipeResponseSearch;
import CooksCorner.CooksCorner.enums.Category;
import CooksCorner.CooksCorner.models.Recipe;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {


    @Query("""
            select new CooksCorner.CooksCorner.dto.responses.RecipeResponseSearch(
            r.id,
            r.name,
            concat(:path, p.link)) from Recipe r
            inner join Photo p on p.recipe.id = r.id
            where ((:search is null)
            or (upper(r.name) like upper(concat('%', :search, '%') ) ))
           
""")
    List<RecipeResponseSearch> searchRecipes(String path, String search, Pageable pageable);

    @Query("""
            select new CooksCorner.CooksCorner.dto.responses.RecipeResponseByCategory(
            r.id,
            r.name,
            r.createdByWhom.fullName,
            concat(:path, p.link)) from Recipe r
            inner join Photo p on p.recipe.id = r.id
            where r.category = :category
          """)
    List<RecipeResponseByCategory> findRecipesByCategory(String path, Category category);
}
