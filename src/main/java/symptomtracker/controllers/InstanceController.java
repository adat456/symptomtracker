package symptomtracker.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import symptomtracker.dtos.CreateInstanceDTO;
import symptomtracker.dtos.InstanceSliceDTO;
import symptomtracker.entities.Instance;
import symptomtracker.exceptions.InvalidAllowableValues;
import symptomtracker.services.InstanceService;

import java.util.List;

@RestController
@RequestMapping("instance")
@RequiredArgsConstructor
public class InstanceController {
    private final InstanceService instanceService;

    @GetMapping("/{instanceId}")
    public ResponseEntity<Instance> get(@PathVariable("instanceId") Integer instanceId) {
        return ResponseEntity.ok(instanceService.get(instanceId));
    }

    // takes in feature ID, and args needed to generate a Pageable
    // by default, obtains content from the first 15 (in descending order)
    @GetMapping("/feature/{featureId}")
    public ResponseEntity<InstanceSliceDTO> getSliceByFeature(
            @PathVariable("featureId") Integer featureId,
            @RequestParam(name = "page-num", defaultValue = "0") Integer pageNum,
            @RequestParam(value = "page-size", defaultValue = "15") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "descending") String sort) {
        return ResponseEntity.ok(instanceService.getSliceByFeature(featureId, pageNum, pageSize, sort));
    }

    @PostMapping
    public ResponseEntity<Instance> create(@Valid @RequestBody CreateInstanceDTO d) throws InvalidAllowableValues {
        return ResponseEntity.ok(instanceService.create(d));
    }

    @PutMapping
    public ResponseEntity<Instance> update(@Valid @RequestBody Instance d) throws InvalidAllowableValues {
        return ResponseEntity.ok(instanceService.update(d));
    }

    @DeleteMapping("/{instanceId}")
    public ResponseEntity<String> delete(@PathVariable("instanceId") Integer instanceId) {
        instanceService.delete(instanceId);
        return ResponseEntity.ok("Instance successfully deleted.");
    }

    @DeleteMapping("/feature/{featureId}")
    public ResponseEntity<String> deleteByFeature(@PathVariable("featureId") Integer featureId) {
        instanceService.deleteByFeature(featureId);
        return ResponseEntity.ok("Feature instances successfully deleted.");
    }
}
