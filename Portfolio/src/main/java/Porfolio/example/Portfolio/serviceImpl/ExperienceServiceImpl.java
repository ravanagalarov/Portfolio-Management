package Porfolio.example.Portfolio.serviceImpl;

import Porfolio.example.Portfolio.config.SecurityUtil;
import Porfolio.example.Portfolio.dtos.ExperienceRequest;
import Porfolio.example.Portfolio.dtos.ExperienceResponse;
import Porfolio.example.Portfolio.entity.Experience;
import Porfolio.example.Portfolio.entity.Portfolio;
import Porfolio.example.Portfolio.entity.User;
import Porfolio.example.Portfolio.exceptionHandling.ResourceNotFoundException;
import Porfolio.example.Portfolio.repository.ExperienceRepository;
import Porfolio.example.Portfolio.repository.PortfolioRepository;
import Porfolio.example.Portfolio.repository.UserRepository;
import Porfolio.example.Portfolio.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ModelMapper mapper;
    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;


    @Override
    @Transactional
    public ExperienceResponse createExperienceInPortfolio(ExperienceRequest request) {
        String username = securityUtil.getCurrentUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found for user " + username));

        Experience experience = mapper.map(request, Experience.class);
        experience.setPortfolio(portfolio);
        Experience saved = experienceRepository.save(experience);

        return mapper.map(saved, ExperienceResponse.class);
    }

    @Override
    @Transactional
    public ExperienceResponse updateExperienceInPortfolio(Long id, ExperienceRequest request) {
        String username = securityUtil.getCurrentUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found for user " + username));

        Experience experience = experienceRepository.findByIdAndPortfolioId(id, portfolio.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Experience not found"));

        mapper.map(request, experience);
        Experience updated = experienceRepository.save(experience);

        return mapper.map(updated, ExperienceResponse.class);
    }

    @Override
    @Transactional
    public void deleteExperienceFromPortfolio(Long id) {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Portfolio not found for user " + username));

        Experience experience = experienceRepository.findByIdAndPortfolioId(id, portfolio.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Experience not found"));

        experienceRepository.delete(experience);
    }

    @Override
    @Transactional
    public List<ExperienceResponse> getExperiences() {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Portfolio not found for user " + username));

        return portfolio.getExperiences()
                .stream()
                .map(experience -> mapper.map(experience, ExperienceResponse.class))
                .toList();

    }

    @Override
    @Transactional
    public ExperienceResponse getExperienceById(Long id) {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Portfolio not found for user " + username));

        Experience experience = experienceRepository.findByIdAndPortfolioId(id, portfolio.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Experience not found"));

        return mapper.map(experience, ExperienceResponse.class);
    }
}
