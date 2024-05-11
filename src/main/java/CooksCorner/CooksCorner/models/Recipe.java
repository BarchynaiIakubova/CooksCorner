package CooksCorner.CooksCorner.models;

import CooksCorner.CooksCorner.enums.Difficulty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "re_gen")
    @SequenceGenerator(name = "re_gen", sequenceName = "re_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String preparationTime;

    private String description;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @ManyToOne
    private Category category;

    private String picture;

    @ManyToOne
    private User createdByWhom;

}