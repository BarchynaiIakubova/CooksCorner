package CooksCorner.CooksCorner.controllers;

import CooksCorner.CooksCorner.dto.requests.RecipeRequest;
import CooksCorner.CooksCorner.dto.responses.*;
import CooksCorner.CooksCorner.enums.Category;
import CooksCorner.CooksCorner.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recipe")
@PreAuthorize("hasAuthority('USER')")
@Tag(name = "Recipe API", description = "Recipe's endpoints")
public class RecipeController {

    private final RecipeService recipeService;


    @PostMapping
    @Operation(summary = "Create a recipe", description = "This method creates recipes")
    Response saveRecipe(@RequestBody RecipeRequest recipeRequest) {
        return recipeService.save(recipeRequest);
    }


    @DeleteMapping("/{recipeId}")
    @Operation(summary = "Delete a recipe", description = "This method deletes recipes")
    Response deleteRecipe(@PathVariable Long recipeId) {

        return recipeService.deleteRecipe(recipeId);
    }


    @GetMapping
    @Operation(summary = "search recipes", description = "This method searches recipes")
    List<RecipeResponseSearch> searchRecipes(@RequestParam(required = false) String search,
                                             @RequestParam(defaultValue = "1") int page,
                                             @RequestParam(defaultValue = "10") int size) {

        return recipeService.searchRecipes(search, page, size);
    }

    @GetMapping("/{recipeId}")
    @Operation(summary = "get recipe by Id", description = "This method gets recipe by id")
    RecipeResponse findById(@PathVariable Long recipeId) {

        return recipeService.findById(recipeId);
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "get recipe by category", description = "This method gets recipe by category")
    List<RecipeByCategoryResponse> findRecipesByCategory(@PathVariable Category category) {

        return recipeService.findRecipesByCategory(category);
    }

//    @GetMapping("/{userId}")
//    @Operation(summary = "get recipes by user", description = "This method gets recipes by user")
//    List<>

}
