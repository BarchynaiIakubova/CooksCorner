package CooksCorner.CooksCorner.controllers;

import CooksCorner.CooksCorner.services.SavedRecipeService;
import CooksCorner.CooksCorner.services.UserLikeRecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@PreAuthorize("hasAuthority('USER')")
@Tag(name = "User API", description = "User like recipes endpoints")
public class UserLikeSaveRecipeController {

    private final UserLikeRecipeService userLikeRecipeService;

    private final SavedRecipeService savedRecipeService;

    @PostMapping("/like_recipe/{recipeId}")
    @Operation(summary = "Recipes liked by user", description = "This method adds liked recipes by user")
    Integer likeRecipe(@PathVariable Long recipeId) {

        return userLikeRecipeService.likeRecipe(recipeId);
    }

    @DeleteMapping("/delete_liked_recipe/{recipeId}")
    @Operation(summary = "Remove liked recipe", description = "This method removes liked recipe")
    Integer removeLikeRecipe(@PathVariable Long recipeId) {

        return userLikeRecipeService.removeLikeRecipe(recipeId);
    }

    @PostMapping("/save_recipe/{recipeId}")
    @Operation(summary = "Recipes saved by user", description = "This method saves recipes")
    Integer saveRecipe(@PathVariable Long recipeId) {

        return savedRecipeService.saveRecipe(recipeId);
    }

    @DeleteMapping("/removed_saved_recipe/{recipeId}")
    @Operation(summary = "Remove liked recipe", description = "This method removes saved recipes")
    Integer deleteSavedRecipe(@PathVariable Long recipeId) {

        return savedRecipeService.deleteSavedRecipe(recipeId);
    }

}
