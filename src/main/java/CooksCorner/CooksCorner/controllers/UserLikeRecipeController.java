package CooksCorner.CooksCorner.controllers;

import CooksCorner.CooksCorner.dto.responses.Response;
import CooksCorner.CooksCorner.services.UserLikeRecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@PreAuthorize("hasAuthority('USER')")
@Tag(name = "User API", description = "User like recipes endpoints")
public class UserLikeRecipeController {

    private final UserLikeRecipeService userLikeRecipeService;

    @PostMapping("/{recipeId}")
    @Operation(summary = "Recipes liked by user", description = "This method adds liked recipes by user")
    Response likeRecipe(@PathVariable Long recipeId) {

        return userLikeRecipeService.likeRecipe(recipeId);
    }

}
