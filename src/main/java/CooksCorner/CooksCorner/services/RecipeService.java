package CooksCorner.CooksCorner.services;

import CooksCorner.CooksCorner.dto.requests.RecipeRequest;
import CooksCorner.CooksCorner.dto.responses.Response;
import CooksCorner.CooksCorner.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public Response save(RecipeRequest recipeRequest, String photoLink) {


        return null;
    }
}
