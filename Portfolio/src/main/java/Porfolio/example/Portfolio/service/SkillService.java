package Porfolio.example.Portfolio.service;

import Porfolio.example.Portfolio.dtos.SkillRequest;
import Porfolio.example.Portfolio.dtos.SkillResponse;

import java.util.List;

public interface SkillService {
    SkillResponse createSkillInPortfolio(SkillRequest request);

    SkillResponse updateSkillInPortfolio(Long id, SkillRequest request);

    void deleteSkillById(Long id);

    void deleteAllSkills();

    List<SkillResponse> getSkills();

    SkillResponse getSkillById(Long id);
 }
