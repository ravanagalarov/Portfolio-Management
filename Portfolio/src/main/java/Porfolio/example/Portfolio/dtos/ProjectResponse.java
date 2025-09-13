package Porfolio.example.Portfolio.dtos;

import Porfolio.example.Portfolio.entity.Portfolio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {

    private Long id;

    private String name;

    private String description;

    private Long portfolioId;
}
