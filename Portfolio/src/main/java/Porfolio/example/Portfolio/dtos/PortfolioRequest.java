package Porfolio.example.Portfolio.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioRequest {
    @NotBlank(message = "Title can't be blank")
    private String title;

    @NotNull(message = "UserId cannot be null")
    private Long userId;

    @Size(max = 10, message = "Maximum 10 projects allowed")
    private List<ProjectRequest> projects;

    @Size(max = 20, message = "Maximum 20 skills allowed")
    private List<SkillRequest> skills;
}
