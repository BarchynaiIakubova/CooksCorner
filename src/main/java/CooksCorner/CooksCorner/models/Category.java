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
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cat_gen")
    @SequenceGenerator(name = "cat_gen", sequenceName = "cat_seq", allocationSize = 1)
    private Long id;

    private String category;
}

