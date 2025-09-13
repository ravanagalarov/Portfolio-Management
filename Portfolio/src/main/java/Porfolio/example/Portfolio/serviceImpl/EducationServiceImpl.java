package Porfolio.example.Portfolio.serviceImpl;

import Porfolio.example.Portfolio.config.SecurityUtil;
import Porfolio.example.Portfolio.dtos.EducationRequest;
import Porfolio.example.Portfolio.dtos.EducationResponse;
import Porfolio.example.Portfolio.entity.Education;
import Porfolio.example.Portfolio.entity.Portfolio;
import Porfolio.example.Portfolio.entity.User;
import Porfolio.example.Portfolio.exceptionHandling.ResourceNotFoundException;
import Porfolio.example.Portfolio.exceptionHandling.UnauthorizedException;
import Porfolio.example.Portfolio.repository.EducationRepository;
import Porfolio.example.Portfolio.repository.PortfolioRepository;
import Porfolio.example.Portfolio.repository.UserRepository;
import Porfolio.example.Portfolio.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final ModelMapper mapper;
    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;

    @Override
    @Transactional
    public EducationResponse addEducationToPortfolio(EducationRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = user.getPortfolio();
        if (portfolio == null) {
            throw new ResourceNotFoundException("Portfolio not found for user " + username);
        }

        Education education = mapper.map(request, Education.class);
        education.setPortfolio(portfolio);
        Education saved = educationRepository.save(education);

        return mapper.map(saved, EducationResponse.class);
    }

    @Override
    @Transactional
    public EducationResponse updateEducationInPortfolio(Long educationId, EducationRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found for user" + username));

        Education education = educationRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found"));

        if (!education.getPortfolio().getId().equals(portfolio.getId())) {
            throw new UnauthorizedException("You can't change education that you don't have");
        }

        education.setDegree(request.getDegree());
        education.setSchool(request.getSchool());
        education.setGraduationYear(request.getGraduationYear());

        Education saved = educationRepository.save(education);
        return mapper.map(saved, EducationResponse.class);

    }

    @Override
    @Transactional
    public void deleteEducationFromPortfolio(Long educationId) {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found for user" + username));

        Education education = educationRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found"));

        if (!education.getPortfolio().getId().equals(portfolio.getId())) {
            throw new UnauthorizedException("You cannot delete education that is not in your portfolio");
        }

        educationRepository.deleteById(educationId);
    }

    @Override
    public List<EducationResponse> getEducations() {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found"));


        return portfolio.getEducations().stream()
                .map(edu -> new EducationResponse(
                        edu.getId(),
                        edu.getSchool(),
                        edu.getDegree(),
                        edu.getGraduationYear(), edu.getPortfolio().getId()))
                .toList();
    }

    @Override
    @Transactional
    public EducationResponse getEducationById(Long eduId) {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found"));

        Education education = educationRepository
                .findByIdAndPortfolioId(eduId, portfolio.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Education not found"));

        return mapper.map(education, EducationResponse.class);

    }
}
