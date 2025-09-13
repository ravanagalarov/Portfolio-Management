package Porfolio.example.Portfolio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String school;
    private String degree;
    private String graduationYear;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

}
