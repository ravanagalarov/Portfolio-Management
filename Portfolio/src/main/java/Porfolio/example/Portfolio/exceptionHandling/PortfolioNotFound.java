package Porfolio.example.Portfolio.exceptionHandling;

public class PortfolioNotFound extends RuntimeException {
    public PortfolioNotFound() {
        super("Portfolio not found for user ");
    }
}
