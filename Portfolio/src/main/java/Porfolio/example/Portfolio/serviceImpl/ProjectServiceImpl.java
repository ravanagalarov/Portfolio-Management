package Porfolio.example.Portfolio.serviceImpl;

import Porfolio.example.Portfolio.config.SecurityUtil;
import Porfolio.example.Portfolio.dtos.ProjectRequest;
import Porfolio.example.Portfolio.dtos.ProjectResponse;
import Porfolio.example.Portfolio.entity.Portfolio;
import Porfolio.example.Portfolio.entity.Project;
import Porfolio.example.Portfolio.entity.User;
import Porfolio.example.Portfolio.exceptionHandling.PortfolioNotFound;
import Porfolio.example.Portfolio.exceptionHandling.ResourceNotFoundException;
import Porfolio.example.Portfolio.repository.PortfolioRepository;
import Porfolio.example.Portfolio.repository.ProjectRepository;
import Porfolio.example.Portfolio.repository.UserRepository;
import Porfolio.example.Portfolio.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper mapper;
    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;

    @Override
    @Transactional
    public ProjectResponse createProjectInPortfolio(ProjectRequest request) {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(PortfolioNotFound::new);

        Project project = mapper.map(request, Project.class);
        project.setPortfolio(portfolio);
        Project created = projectRepository.save(project);

        return mapper.map(created, ProjectResponse.class);
    }

    @Override
    @Transactional
    public ProjectResponse updateProjectDetails(Long id, ProjectRequest request) {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(PortfolioNotFound::new);

        Project project = projectRepository.findByIdAndPortfolioId(id, portfolio.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        mapper.map(request, project);
        Project updated = projectRepository.save(project);

        return mapper.map(updated, ProjectResponse.class);
    }

    @Override
    @Transactional
    public List<ProjectResponse> getProjects() {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(PortfolioNotFound::new);

        return portfolio.getProjects().stream()
                .map(project -> mapper.map(project, ProjectResponse.class))
                .toList();
    }

    @Override
    @Transactional
    public ProjectResponse getProjectById(Long projectId) {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(PortfolioNotFound::new);

        Project project = projectRepository.findByIdAndPortfolioId(projectId, portfolio.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        return mapper.map(project, ProjectResponse.class);
    }

    @Override
    @Transactional
    public void deleteProjectById(Long id) {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(PortfolioNotFound::new);

        Project project = projectRepository.findByIdAndPortfolioId(id, portfolio.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        projectRepository.delete(project);
    }

    @Override
    @Transactional
    public void deleteAllProjects() {
        String username = securityUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository.findByUser(user)
                .orElseThrow(PortfolioNotFound::new);

        projectRepository.deleteAllByPortfolioId(portfolio.getId());

    }
}
