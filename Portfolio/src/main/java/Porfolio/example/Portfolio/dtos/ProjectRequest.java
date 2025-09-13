package Porfolio.example.Portfolio.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {
    @NotBlank(message = "Project name can't be blank")
    private String name;

    @Size(max = 500, message = "Max 500 chars")
    private String description;
}
