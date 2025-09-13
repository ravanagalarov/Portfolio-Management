package Porfolio.example.Portfolio.repository;

import Porfolio.example.Portfolio.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    Optional<Experience> findByIdAndPortfolioId(Long experineceId, Long portfolioId);


}
