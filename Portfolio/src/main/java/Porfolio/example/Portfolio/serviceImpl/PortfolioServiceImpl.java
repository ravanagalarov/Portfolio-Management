package Porfolio.example.Portfolio.serviceImpl;

import Porfolio.example.Portfolio.config.SecurityUtil;
import Porfolio.example.Portfolio.dtos.PortfolioRequest;
import Porfolio.example.Portfolio.dtos.PortfolioResponse;
import Porfolio.example.Portfolio.entity.Portfolio;
import Porfolio.example.Portfolio.entity.User;
import Porfolio.example.Portfolio.exceptionHandling.PortfolioNotFound;
import Porfolio.example.Portfolio.exceptionHandling.ResourceNotFoundException;
import Porfolio.example.Portfolio.repository.PortfolioRepository;
import Porfolio.example.Portfolio.repository.UserRepository;
import Porfolio.example.Portfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final ModelMapper mapper;
    private final SecurityUtil securityUtil;
    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public PortfolioResponse updatePortfolio(PortfolioRequest request) {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(PortfolioNotFound::new);

        mapper.map(request, portfolio);
        Portfolio updated = portfolioRepository.save(portfolio);

        return mapper.map(updated, PortfolioResponse.class);
    }

    @Override
    @Transactional
    public PortfolioResponse getPortfolio() {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(PortfolioNotFound::new);

        return mapper.map(portfolio, PortfolioResponse.class);
    }


}
