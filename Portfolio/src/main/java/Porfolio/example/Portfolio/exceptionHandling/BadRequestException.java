package Porfolio.example.Portfolio.exceptionHandling;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
