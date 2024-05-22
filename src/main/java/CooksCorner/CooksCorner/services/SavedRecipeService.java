package CooksCorner.CooksCorner.services;

import CooksCorner.CooksCorner.models.SavedRecipes;
import CooksCorner.CooksCorner.models.User;
import CooksCorner.CooksCorner.models.UserLikeRecipe;
import CooksCorner.CooksCorner.repositories.SavedRecipeRepository;
import CooksCorner.CooksCorner.validations.UserValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavedRecipeService {

    private final SavedRecipeRepository savedRecipeRepository;

    private final UserValidate userValidate;


    public Integer saveRecipe(Long recipeId) {
        User user = userValidate.getByAuthentication();

        savedRecipeRepository.save(SavedRecipes.builder()
                .recipeId(recipeId)
                .userId(user.getId())
                .build());

        return savedRecipeRepository.saveRecipe(recipeId);
    }

    public Integer deleteSavedRecipe(Long recipeId) {

        User user = userValidate.getByAuthentication();

        savedRecipeRepository.removeSavedRecipe(user.getId(), recipeId);

        return savedRecipeRepository.saveRecipe(recipeId);
    }
}
