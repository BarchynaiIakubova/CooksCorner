package CooksCorner.CooksCorner.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "savedRecipes")
public class SavedRecipes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "save_gen")
    @SequenceGenerator(name = "save_gen", sequenceName = "save_seq", allocationSize = 1)
    private Long id;

    private Long userId;

    private Long recipeId;
}
