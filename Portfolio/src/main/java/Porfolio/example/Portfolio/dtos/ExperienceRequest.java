package Porfolio.example.Portfolio.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceRequest {
    @NotBlank(message = "Company name can't be blank")
    private String company;

    @NotBlank(message = "Position can't be blank")
    private String position;

    @Pattern(regexp = "^\\d+\\s(months|years)$", message = "Duration must be like '6 months' or '2 years'")
    private String duration;
}
