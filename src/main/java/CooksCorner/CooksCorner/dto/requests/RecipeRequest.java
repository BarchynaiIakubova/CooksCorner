package CooksCorner.CooksCorner.dto.requests;

import CooksCorner.CooksCorner.enums.Category;
import CooksCorner.CooksCorner.enums.Difficulty;
import CooksCorner.CooksCorner.models.Ingredient;
import CooksCorner.CooksCorner.models.User;

import java.util.List;

public record RecipeRequest(
        String name,

        String preparationTime,

        String description,

        Difficulty difficulty,

        Category category,

        String photoLink,

        User userId,

        List<Ingredient> ingredients
) {

}
