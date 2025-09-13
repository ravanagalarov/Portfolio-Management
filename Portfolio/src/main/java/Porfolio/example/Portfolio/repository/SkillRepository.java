package Porfolio.example.Portfolio.repository;

import Porfolio.example.Portfolio.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    Optional<Skill> findByIdAndPortfolioId(Long id, Long portfolioId);
    void deleteAllByPortfolioId(Long portfolioId);

}
