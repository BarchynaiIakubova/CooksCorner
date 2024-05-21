package CooksCorner.CooksCorner.services;

import CooksCorner.CooksCorner.dto.responses.Response;
import CooksCorner.CooksCorner.repositories.UserLikeRecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLikeRecipeService {

    private final UserLikeRecipeRepository userLikeRecipeRepository;

    public Response likeRecipe(Long recipeId) {

        return null;
    }
}
