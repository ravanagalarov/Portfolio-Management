package Porfolio.example.Portfolio.repository;

import Porfolio.example.Portfolio.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EducationRepository extends JpaRepository<Education, Long> {
    Optional<Education> findById(Long educationId);
    Optional<Education> findByIdAndPortfolioId(Long id, Long portfolioId);


}
