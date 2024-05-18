package CooksCorner.CooksCorner.controllers;

import CooksCorner.CooksCorner.dto.requests.RecipeRequest;
import CooksCorner.CooksCorner.dto.responses.Response;
import CooksCorner.CooksCorner.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recipe")
@Tag(name = "Recipe API", description = "Recipe's endpoints")
public class RecipeController {

    private final RecipeService recipeService;

    @Operation(summary = "Create a recipe", description = "This method creates recipes")
    @PostMapping
    Response saveRecipe(@RequestBody RecipeRequest recipeRequest, @RequestParam String photoLink) {

        return recipeService.save(recipeRequest, photoLink);
    }
}
