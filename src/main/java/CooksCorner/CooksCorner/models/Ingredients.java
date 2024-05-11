package CooksCorner.CooksCorner.models;

import CooksCorner.CooksCorner.enums.Unit;
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
public class Ingredients {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "in_gen")
    @SequenceGenerator(name = "in_gen", sequenceName = "in_seq", allocationSize = 1)
    private Long id;

    private String name;

    private Double quantityOfIngredients;

    @OneToOne
    private Recipe recipe;

    @Enumerated(EnumType.STRING)
    private Unit unit;
}
