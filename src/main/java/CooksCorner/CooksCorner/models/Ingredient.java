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
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "in_gen")
    @SequenceGenerator(name = "in_gen", sequenceName = "in_seq", allocationSize = 1)
    private Long id;

    private String name;

    private Double quantityOfIngredients;

    private String unit;

    @ManyToOne
    private Recipe recipe;

}
