package Porfolio.example.Portfolio.service;

import Porfolio.example.Portfolio.dtos.ExperienceRequest;
import Porfolio.example.Portfolio.dtos.ExperienceResponse;

import java.util.List;

public interface ExperienceService {
    ExperienceResponse createExperienceInPortfolio(ExperienceRequest request);

    ExperienceResponse updateExperienceInPortfolio(Long id, ExperienceRequest request);

    void deleteExperienceFromPortfolio(Long id);

    List<ExperienceResponse> getExperiences();

    ExperienceResponse getExperienceById(Long id);
}
