package CooksCorner.CooksCorner.dto.responses;

import CooksCorner.CooksCorner.enums.Category;
import CooksCorner.CooksCorner.enums.Difficulty;

import java.util.List;

public record RecipeResponse(

        String createdBy,

        Long id,

        String name,

        String preparationTime,

        String description,

        Difficulty difficulty,

        Category category,

        PhotoResponseForGetting photoResponse,

        List<IngredientResponse> ingredientResponses

) {




}
