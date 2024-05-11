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
@Table(name = "followers_following")
public class FollowersFollowing {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ff_gen")
    @SequenceGenerator(name = "ff_gen", sequenceName = "ff_seq", allocationSize = 1)
    private Long id;

    private Long followerId;

    private Long followingId;


}
