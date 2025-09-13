package Porfolio.example.Portfolio.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceResponse {
    private Long id;

    private String company;

    private String position;

    private String duration;

    private Long portfolioId;
}
