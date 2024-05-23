package CooksCorner.CooksCorner.dto.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record RecipeResponseByCategory (

        Long id,

        String recipeName,

        String createdBy,

        String photoLink

) {
}



//public record RecipeResponseByCategory(
//
//        Long id,
//
//        String recipeName,
//
//        String createdBy,
//
//        String photoLink,
//
//        Integer numberOfLikes,
//
//        Integer numberOfSaves
//
//
//) {
//}
