package CooksCorner.CooksCorner.dto.requests;

public record AuthenticationRequest(

        String email,

        String password
) {
}
