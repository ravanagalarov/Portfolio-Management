package Porfolio.example.Portfolio.controller;

import Porfolio.example.Portfolio.dtos.PortfolioRequest;
import Porfolio.example.Portfolio.dtos.PortfolioResponse;
import Porfolio.example.Portfolio.serviceImpl.PortfolioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioServiceImpl portfolioService;

    @GetMapping
    public ResponseEntity<PortfolioResponse> getPortfolio() {
        return ResponseEntity.ok(portfolioService.getPortfolio());
    }

    @PutMapping
    public ResponseEntity<PortfolioResponse> updatePortfolio(@RequestBody PortfolioRequest request) {
        PortfolioResponse response = portfolioService.updatePortfolio(request);
        return ResponseEntity.ok(response);
    }
}



