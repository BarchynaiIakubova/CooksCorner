package CooksCorner.CooksCorner.dto.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PhotoResponseForGetting {

    private Long id;

    private String link;
}
