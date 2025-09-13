package Porfolio.example.Portfolio.service;

import Porfolio.example.Portfolio.dtos.ProjectRequest;
import Porfolio.example.Portfolio.dtos.ProjectResponse;

import java.util.List;

public interface ProjectService {
    ProjectResponse createProjectInPortfolio(ProjectRequest request);

    ProjectResponse updateProjectDetails(Long id, ProjectRequest request);

    List<ProjectResponse> getProjects();

    ProjectResponse getProjectById(Long projectId);

    void deleteProjectById(Long id);

    void deleteAllProjects();


}
