package CooksCorner.CooksCorner.dto.requests;

public record RegisterRequest(

        String email,

        String fullName,

        String password
) {
}
