package Porfolio.example.Portfolio.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationRequest {
    @NotBlank(message = "School name cannot be blank")
    private String school;

    @NotBlank(message = "Degree cannot be blank")
    private String degree;

    @Pattern(regexp = "^[0-9]{4}$", message = "Graduation year must be a 4-digit year")
    private String graduationYear;
}
