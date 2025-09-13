package Porfolio.example.Portfolio.service;

import Porfolio.example.Portfolio.dtos.EducationRequest;
import Porfolio.example.Portfolio.dtos.EducationResponse;

import java.util.List;

public interface EducationService {
    EducationResponse addEducationToPortfolio(EducationRequest request);

    EducationResponse updateEducationInPortfolio(Long educationId, EducationRequest request);

    void deleteEducationFromPortfolio(Long educationId);

    List<EducationResponse> getEducations();

    EducationResponse getEducationById(Long id);


}
