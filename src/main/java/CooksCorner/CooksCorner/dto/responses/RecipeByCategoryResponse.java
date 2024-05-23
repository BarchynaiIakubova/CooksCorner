package CooksCorner.CooksCorner.dto.responses;

public record RecipeByCategoryResponse(

        Long id,

        String recipeName,

        String createdBy,

        String photoLink,

        Integer numberOfLikes,

        Integer numberOfSaves
) {
}
