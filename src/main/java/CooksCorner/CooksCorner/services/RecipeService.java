package CooksCorner.CooksCorner.services;

import CooksCorner.CooksCorner.dto.requests.IngredientRequest;
import CooksCorner.CooksCorner.dto.requests.RecipeRequest;
import CooksCorner.CooksCorner.dto.responses.*;
import CooksCorner.CooksCorner.enums.Category;
import CooksCorner.CooksCorner.exceptions.NotFoundException;
import CooksCorner.CooksCorner.models.Ingredient;
import CooksCorner.CooksCorner.models.Photo;
import CooksCorner.CooksCorner.models.Recipe;
import CooksCorner.CooksCorner.repositories.*;
import CooksCorner.CooksCorner.validations.PhotoValidate;
import CooksCorner.CooksCorner.validations.UserValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    private final UserValidate userValidate;

    private final UserLikeRecipeService userLikeRecipeService;

    private final IngredientRepository ingredientRepository;

    private final PhotoRepository photoRepository;//todo убрать

    private final PhotoValidate photoValidate;

    private final S3Service s3Service;

    private final UserLikeRecipeRepository userLikeRecipeRepository;

    private final SavedRecipeRepository savedRecipeRepository;//todo убрать

    @Value("${cloud.aws.bucket.path}")
    private String path;

    public Response save(RecipeRequest recipeRequest) {

//        Long photoId = photoRepository.save(Photo.builder()
//                .link(recipeRequest.photoLink().substring(path.length()))
//                .build()).getId();

        Photo photo = photoValidate.findPhotoByLink(recipeRequest.photoLink());

        Recipe recipe = Recipe.builder()
                .category(recipeRequest.category())
                .name(recipeRequest.name())
                .description(recipeRequest.description())
                .preparationTime(recipeRequest.preparationTime())
                .createdByWhom(userValidate.getByAuthentication())
//                .photo(photoValidate.findPhotoByLink(recipeRequest.photoLink()))
                .difficulty(recipeRequest.difficulty())
                .build();

        recipeRepository.save(recipe);

        photo.setRecipe(recipe);

        for (IngredientRequest ingredient: recipeRequest.ingredients()) {

            ingredientRepository.save(Ingredient.builder()
                    .name(ingredient.name())
                    .quantityOfIngredients(ingredient.quantityOfIngredients())
                    .unit(ingredient.unit())
                    .recipe(recipe)
                    .build());
        }

        return new Response("Recipe and ingredients saved successfully");
    }

    public Response deleteRecipe(Long recipeId) {

        Recipe recipe = findRecipeById(recipeId);

        String photoLink = photoRepository.findPhotoLinkByRecipeId(recipeId);

        ingredientRepository.deleteAll(findIngredientsByRecipeId(recipeId));

//        String photoLink = photo.getLink();

//        photoRepository.delete(photo);

        s3Service.deletePath(path + photoLink);

        recipeRepository.delete(recipe);



        return new Response("The recipe is deleted");
    }


    public List<RecipeResponseSearch> searchRecipes(String search, int page, int size) {

        String searchName = (search != null) ? search.replaceAll("\\s+", " ").trim() : null;

        return recipeRepository.searchRecipes(path, searchName, PageRequest.of(page - 1, size));
    }

    public RecipeResponse findById(Long recipeId) {

        Recipe recipe = findRecipeById(recipeId);

        Photo photo = photoRepository.findPhotoByRecipeId(recipeId);

        List<Ingredient> ingredients = findIngredientsByRecipeId(recipeId);

        List<IngredientResponse> ingredientResponses = ingredients.stream()
                .map(ingredient -> new IngredientResponse(
                        ingredient.getId(),
                        ingredient.getName(),
                        ingredient.getQuantityOfIngredients(),
                        ingredient.getUnit()
                ))
                .collect(Collectors.toList());

        return new RecipeResponse(
                recipe.getCreatedByWhom().getFullName(),
                recipeId,
                recipe.getName(),
                recipe.getPreparationTime(),
                recipe.getDescription(),
                recipe.getDifficulty(),
                recipe.getCategory(),
                new PhotoResponseForGetting(photo.getId(), photo.getLink()),
                ingredientResponses
        );
    }


    public Recipe findRecipeById(Long recipeId) {

        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new NotFoundException("The recipe with this id is not found"));
    }

    public List<Ingredient> findIngredientsByRecipeId(Long recipeId) {

        return ingredientRepository.findbyRecipeId(recipeId);
    }

    public List<RecipeByCategoryResponse> findRecipesByCategory(Category category) {

//        return recipeRepository.findRecipesByCategory(path, category);


        List<RecipeByCategoryResponse> result = new ArrayList<>();

        List<Recipe> recipes = recipeRepository.findAllByCategory(category);
        recipes.forEach(
                recipe -> {
                    Photo photo = s3Service.getByRecipe(recipe);
                    Integer countOfLikes = userLikeRecipeService.getCountOfLikesByRecipeId(recipe.getId());
                    RecipeByCategoryResponse dto = toDto(recipe, photo.getLink(), countOfLikes);
                    result.add(dto);
                }
        );


        return result;

    }

    private RecipeByCategoryResponse toDto(Recipe recipe, String link, Integer numberOfLikes) {

        return new RecipeByCategoryResponse(
                recipe.getId(),
                recipe.getName(),
                recipe.getCreatedByWhom().getFullName(),
                String.join("", path, link),
                numberOfLikes,
                null
        );
    }
}
