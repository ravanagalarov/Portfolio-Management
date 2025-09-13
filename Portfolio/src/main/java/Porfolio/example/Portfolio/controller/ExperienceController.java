package Porfolio.example.Portfolio.controller;

import Porfolio.example.Portfolio.dtos.ExperienceRequest;
import Porfolio.example.Portfolio.dtos.ExperienceResponse;
import Porfolio.example.Portfolio.serviceImpl.ExperienceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auth/experience")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceServiceImpl experienceService;

    @GetMapping
    public ResponseEntity<List<ExperienceResponse>> getAllExperiences() {
        return ResponseEntity.ok(experienceService.getExperiences());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienceResponse> getExperienceById(@PathVariable Long id) {
        return ResponseEntity.ok(experienceService.getExperienceById(id));
    }

    @PostMapping
    public ResponseEntity<ExperienceResponse> createExperience(@RequestBody ExperienceRequest request) {
        ExperienceResponse response = experienceService.createExperienceInPortfolio(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperienceResponse> updateExperience(
            @PathVariable Long id,
            @RequestBody ExperienceRequest request) {
        return ResponseEntity.ok(experienceService.updateExperienceInPortfolio(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperienceFromPortfolio(id);
        return ResponseEntity.noContent().build();
    }
}


