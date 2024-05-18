package CooksCorner.CooksCorner.services;

import CooksCorner.CooksCorner.dto.requests.RecipeRequest;
import CooksCorner.CooksCorner.dto.responses.Response;
import CooksCorner.CooksCorner.enums.Category;
import CooksCorner.CooksCorner.models.Recipe;
import CooksCorner.CooksCorner.repositories.RecipeRepository;
import CooksCorner.CooksCorner.validations.UserValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    private final UserValidate userValidate;

    public Response save(RecipeRequest recipeRequest, String photoLink) {

        Recipe.builder()
                .category(Category.BREAKFAST)
                .name(recipeRequest.name())
                .description(recipeRequest.description())
                .preparationTime(recipeRequest.preparationTime())
                .createdByWhom(userValidate.getByAuthentication())
                .picture(photoLink)
                .build();

        return null;
    }
}
