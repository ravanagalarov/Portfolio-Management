package Porfolio.example.Portfolio.serviceImpl;

import Porfolio.example.Portfolio.config.SecurityUtil;
import Porfolio.example.Portfolio.dtos.SkillRequest;
import Porfolio.example.Portfolio.dtos.SkillResponse;
import Porfolio.example.Portfolio.entity.Portfolio;
import Porfolio.example.Portfolio.entity.Skill;
import Porfolio.example.Portfolio.entity.User;
import Porfolio.example.Portfolio.exceptionHandling.PortfolioNotFound;
import Porfolio.example.Portfolio.exceptionHandling.ResourceNotFoundException;
import Porfolio.example.Portfolio.repository.PortfolioRepository;
import Porfolio.example.Portfolio.repository.SkillRepository;
import Porfolio.example.Portfolio.repository.UserRepository;
import Porfolio.example.Portfolio.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final ModelMapper mapper;
    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;

    @Override
    @Transactional
    public SkillResponse createSkillInPortfolio(SkillRequest request) {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(PortfolioNotFound::new);

        Skill skill = mapper.map(request, Skill.class);
        skill.setPortfolio(portfolio);
        Skill created = skillRepository.save(skill);

        return mapper.map(created, SkillResponse.class);
    }

    @Override
    @Transactional
    public SkillResponse updateSkillInPortfolio(Long id, SkillRequest request) {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(PortfolioNotFound::new);

        Skill skill = skillRepository.findByIdAndPortfolioId(id, portfolio.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));

        mapper.map(request, skill);
        Skill updated = skillRepository.save(skill);

        return mapper.map(updated, SkillResponse.class);
    }

    @Override
    @Transactional
    public void deleteSkillById(Long id) {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(PortfolioNotFound::new);

        Skill skill = skillRepository.findByIdAndPortfolioId(id, portfolio.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));

        skillRepository.delete(skill);
    }

    @Override
    @Transactional
    public void deleteAllSkills() {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(PortfolioNotFound::new);

        skillRepository.deleteAllByPortfolioId(portfolio.getId());
    }

    @Override
    @Transactional
    public List<SkillResponse> getSkills() {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(PortfolioNotFound::new);

        return portfolio.getSkills().stream()
                .map(skill -> mapper.map(skill, SkillResponse.class))
                .toList();
    }

    @Override
    @Transactional
    public SkillResponse getSkillById(Long id) {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(PortfolioNotFound::new);

        Skill skill = skillRepository.findByIdAndPortfolioId(id, portfolio.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));

        return mapper.map(skill, SkillResponse.class);

    }
}
