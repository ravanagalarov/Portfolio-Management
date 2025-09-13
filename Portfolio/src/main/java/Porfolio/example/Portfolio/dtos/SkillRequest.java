package Porfolio.example.Portfolio.dtos;

import Porfolio.example.Portfolio.enums.Level;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillRequest {
    @NotBlank(message = "Skill name can't be blank")
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Level level;
}
