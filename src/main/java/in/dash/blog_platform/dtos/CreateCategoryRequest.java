package in.dash.blog_platform.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateCategoryRequest {
@NotBlank(message = "Name is required")
@Size(min = 2,max = 8,message = "Length must be less than {max} and greater than {min}")
    private String name;

    public CreateCategoryRequest(String name) {
        this.name = name;
    }

    public CreateCategoryRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
