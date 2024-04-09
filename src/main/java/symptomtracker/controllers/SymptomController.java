package symptomtracker.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import symptomtracker.dtos.CreateSymptomDTO;
import symptomtracker.entities.Symptom;
import symptomtracker.services.SymptomService;

@RestController
@RequiredArgsConstructor
@RequestMapping("symptom")
public class SymptomController {
    private final SymptomService symptomService;

    @PostMapping
    private ResponseEntity<Symptom> create(@RequestBody @Valid CreateSymptomDTO d) {
        return ResponseEntity.ok(symptomService.create(d));
    }

    @PutMapping("/update-name-desc")
    private ResponseEntity<Symptom> updateNameDescription(@RequestBody @Valid Symptom s) {
        return ResponseEntity.ok(symptomService.updateNameDescription(s));
    }

    @PutMapping("/{symptomId}/update-category/{categoryId}")
    private ResponseEntity<Symptom> updateCategory(@PathVariable("symptomId") Integer symptomId, @PathVariable("categoryId") Integer categoryId) {
        return ResponseEntity.ok(symptomService.updateCategory(symptomId, categoryId));
    }

    @DeleteMapping("/{symptomId}")
    private ResponseEntity<String> delete(@PathVariable("symptomId") Integer symptomId) {
        symptomService.delete(symptomId);
        return ResponseEntity.ok("Symptom deleted.");
    }
}
