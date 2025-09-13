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
    private User user;

    private List<Project> projects = new ArrayList<>();

    private List<Experience> experiences = new ArrayList<>();

    private List<Skill> skills = new ArrayList<>();

    private List<Education> educations = new ArrayList<>();
}
