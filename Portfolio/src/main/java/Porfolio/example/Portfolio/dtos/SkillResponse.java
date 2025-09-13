package Porfolio.example.Portfolio.dtos;

import Porfolio.example.Portfolio.entity.Portfolio;
import Porfolio.example.Portfolio.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillResponse {

    private Long id;

    private String name;

    private Level level;

    private Portfolio portfolio;
}
