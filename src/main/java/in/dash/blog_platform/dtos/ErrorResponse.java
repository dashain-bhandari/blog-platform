package in.dash.blog_platform.dtos;

public record ErrorResponse(
        int status,
        String message,
        String details
) {

}