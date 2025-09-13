package Porfolio.example.Portfolio.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationResponse {
    private Long id;
    private String school;
    private String degree;
    private String graduationYear;
    private Long portfolioId;
}
