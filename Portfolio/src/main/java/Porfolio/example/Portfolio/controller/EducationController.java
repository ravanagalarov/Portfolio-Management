package Porfolio.example.Portfolio.controller;

import Porfolio.example.Portfolio.dtos.EducationRequest;
import Porfolio.example.Portfolio.dtos.EducationResponse;
import Porfolio.example.Portfolio.serviceImpl.EducationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auth/education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationServiceImpl educationService;

    @GetMapping
    public ResponseEntity<List<EducationResponse>> getEducations() {
        return ResponseEntity.ok(educationService.getEducations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationResponse> getEducationById(@PathVariable Long id) {
        return ResponseEntity.ok(educationService.getEducationById(id));
    }

    @PostMapping
    public ResponseEntity<EducationResponse> addEducation(@jakarta.validation.Valid @RequestBody EducationRequest request) {
        EducationResponse response = educationService.addEducationToPortfolio(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EducationResponse> updateEducation(@PathVariable Long id,
                                                             @jakarta.validation.Valid @RequestBody EducationRequest request) {
        EducationResponse response = educationService.updateEducationInPortfolio(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        educationService.deleteEducationFromPortfolio(id);
        return ResponseEntity.noContent().build();
    }
}

