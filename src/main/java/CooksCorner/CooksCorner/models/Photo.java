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
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "in_gen")
    @SequenceGenerator(name = "in_gen", sequenceName = "in_seq", allocationSize = 1)
    private Long id;

    private String link;

    @ManyToOne
    private User user;

    @ManyToOne
    private Recipe recipe;

    public Photo(String key) {

        this.link = key;
    }
}
