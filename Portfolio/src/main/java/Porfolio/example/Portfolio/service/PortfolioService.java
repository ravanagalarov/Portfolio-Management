package Porfolio.example.Portfolio.service;

import Porfolio.example.Portfolio.dtos.PortfolioRequest;
import Porfolio.example.Portfolio.dtos.PortfolioResponse;

public interface PortfolioService {
    PortfolioResponse updatePortfolio(PortfolioRequest request);

    PortfolioResponse getPortfolio();

}
