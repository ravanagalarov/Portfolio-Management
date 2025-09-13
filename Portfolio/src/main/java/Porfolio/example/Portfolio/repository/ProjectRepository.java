package Porfolio.example.Portfolio.repository;

import Porfolio.example.Portfolio.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByIdAndPortfolioId(Long projectId, Long portfolioId);
    void deleteAllByPortfolioId(Long portfolioId);


}
