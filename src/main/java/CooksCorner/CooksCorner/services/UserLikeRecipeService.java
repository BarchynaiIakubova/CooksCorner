package CooksCorner.CooksCorner.services;

import CooksCorner.CooksCorner.dto.responses.Response;
import CooksCorner.CooksCorner.models.User;
import CooksCorner.CooksCorner.models.UserLikeRecipe;
import CooksCorner.CooksCorner.repositories.UserLikeRecipeRepository;
import CooksCorner.CooksCorner.validations.UserValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLikeRecipeService {

    private final UserLikeRecipeRepository userLikeRecipeRepository;

    private final UserValidate userValidate;

    public Integer likeRecipe(Long recipeId) {

        User user = userValidate.getByAuthentication();

        userLikeRecipeRepository.save(UserLikeRecipe.builder()
                .recipeId(recipeId)
                .userId(user.getId())
                .build());

        return userLikeRecipeRepository.likeRecipe(recipeId);
    }

    public Integer removeLikeRecipe(Long recipeId) {

        User user = userValidate.getByAuthentication();

        userLikeRecipeRepository.removeLikeRecipe(user.getId(), recipeId);

        return userLikeRecipeRepository.likeRecipe(recipeId);
    }


    public Integer getCountOfLikesByRecipeId(Long recipeId){
        return userLikeRecipeRepository.countAllByRecipeId(recipeId);
    }
}
