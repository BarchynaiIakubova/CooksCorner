package CooksCorner.CooksCorner.dto.responses;

public record IngredientResponse(

        Long id,

        String ingredientName,

        Double quantityOfIngredients,

        String unit
) {
}
