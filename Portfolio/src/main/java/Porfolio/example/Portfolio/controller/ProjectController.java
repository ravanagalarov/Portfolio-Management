package Porfolio.example.Portfolio.controller;

import Porfolio.example.Portfolio.dtos.ProjectRequest;
import Porfolio.example.Portfolio.dtos.ProjectResponse;
import Porfolio.example.Portfolio.serviceImpl.ProjectServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectServiceImpl projectService;

    @GetMapping
    public List<ProjectResponse> getProjects() {
        return projectService.getProjects();
    }

    @GetMapping("/{id}")
    public ProjectResponse getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public ProjectResponse createProject(@jakarta.validation.Valid @RequestBody ProjectRequest request) {
        return projectService.createProjectInPortfolio(request);
    }

    @PutMapping("/{id}")
    public ProjectResponse updateProject(@PathVariable Long id, @jakarta.validation.Valid @RequestBody ProjectRequest request) {
        return projectService.updateProjectDetails(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProjectById(id);
    }

    @DeleteMapping
    public void deleteAllProjects() {
        projectService.deleteAllProjects();
    }
}
