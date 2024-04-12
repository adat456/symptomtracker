package symptomtracker.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import symptomtracker.dtos.CreateFeatureDTO;
import symptomtracker.entities.Feature;
import symptomtracker.exceptions.InvalidAllowableValues;
import symptomtracker.services.FeatureService;

@RestController
@RequiredArgsConstructor
@RequestMapping("feature")
public class FeatureController {
    private final FeatureService featureService;

    @GetMapping("/{featureId}")
    private ResponseEntity<Feature> get(@PathVariable("featureId") Integer featureId) {
        return ResponseEntity.ok(featureService.get(featureId));
    }

    @PostMapping
    private ResponseEntity<Feature> create(@RequestBody @Valid CreateFeatureDTO d) throws InvalidAllowableValues {
        return ResponseEntity.ok(featureService.create(d));
    }

    @PutMapping
    private ResponseEntity<Feature> update(@RequestBody @Valid Feature f) throws InvalidAllowableValues {
        return ResponseEntity.ok(featureService.update(f));
    }

    @DeleteMapping("/{featureId}")
    private ResponseEntity<String> delete(@PathVariable("featureId") Integer featureId) {
        featureService.delete(featureId);
        return ResponseEntity.ok("Feature successfully deleted.");
    }

}
