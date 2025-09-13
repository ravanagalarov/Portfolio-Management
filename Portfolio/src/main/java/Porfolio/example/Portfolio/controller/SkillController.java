package Porfolio.example.Portfolio.controller;

import Porfolio.example.Portfolio.dtos.SkillRequest;
import Porfolio.example.Portfolio.dtos.SkillResponse;
import Porfolio.example.Portfolio.serviceImpl.SkillServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auth/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillServiceImpl skillService;

    // ✅ Bütün skill-ləri gətir
    @GetMapping
    public ResponseEntity<List<SkillResponse>> getSkills() {
        return ResponseEntity.ok(skillService.getSkills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillResponse> getSkillById(@PathVariable Long id) {
        return ResponseEntity.ok(skillService.getSkillById(id));
    }

    @PostMapping
    public ResponseEntity<SkillResponse> createSkill(@jakarta.validation.Valid @RequestBody SkillRequest request) {
        SkillResponse response = skillService.createSkillInPortfolio(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillResponse> updateSkill(@PathVariable Long id,
                                                     @jakarta.validation.Valid @RequestBody SkillRequest request) {
        SkillResponse response = skillService.updateSkillInPortfolio(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkillById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllSkills() {
        skillService.deleteAllSkills();
        return ResponseEntity.noContent().build();
    }
}

