package Porfolio.example.Portfolio.repository;

import Porfolio.example.Portfolio.entity.Portfolio;
import Porfolio.example.Portfolio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Optional<Portfolio> findByUser(User user);
    
    @Query("SELECT p FROM Portfolio p WHERE p.user.id = :userId")
    Optional<Portfolio> findByUserId(@Param("userId") Long userId);
}
