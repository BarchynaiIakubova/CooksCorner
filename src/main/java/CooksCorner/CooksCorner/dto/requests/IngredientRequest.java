package CooksCorner.CooksCorner.dto.requests;

public record IngredientRequest(

        String name,

        Double quantityOfIngredients,

        String unit
) {
}
