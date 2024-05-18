package CooksCorner.CooksCorner.dto.responses;

public record ExceptionResponse(
        String exceptionClassname,

        String message
) {
}
