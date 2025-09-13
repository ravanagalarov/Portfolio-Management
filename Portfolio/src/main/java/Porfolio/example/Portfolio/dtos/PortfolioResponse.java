package Porfolio.example.Portfolio.dtos;

import Porfolio.example.Portfolio.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioResponse {

    private Long id;
    private String title;

    private List<ProjectResponse> projects = new ArrayList<>();
    private List<ExperienceResponse> experiences = new ArrayList<>();
    private List<SkillResponse> skills = new ArrayList<>();
    private List<EducationResponse> educations = new ArrayList<>();
}
