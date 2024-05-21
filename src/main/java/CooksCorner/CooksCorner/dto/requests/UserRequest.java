package CooksCorner.CooksCorner.dto.requests;

public record UserRequest(

        String fullName,

        String bio,

        String photoLink
) {
}
