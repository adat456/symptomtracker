package symptomtracker.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import symptomtracker.dtos.CreateFeatureDTO;
import symptomtracker.entities.Feature;
import symptomtracker.entities.FeatureType;
import symptomtracker.entities.Symptom;
import symptomtracker.exceptions.InvalidAllowableValues;
import symptomtracker.repos.FeatureRepo;
import symptomtracker.repos.SymptomRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeatureService {
    private final SymptomRepo symptomRepo;
    private final FeatureRepo featureRepo;

    public Feature get(Integer featureId) {
        return featureRepo.findById(featureId).orElseThrow(() -> new EntityNotFoundException("Unable to find feature."));
    }

    public Feature create(CreateFeatureDTO d) throws InvalidAllowableValues {
        Symptom symptom = symptomRepo.findById(d.getSymptomId()).orElseThrow(() -> new EntityNotFoundException("Unable to find symptom."));
        validateAllowableValues(d.getType(), d.getAllowableValues());
        return featureRepo.save(new Feature(symptom, d.getName(), d.getType(), d.getAllowableValues()));
    }

    // ensures that allowable values are appropriate for scale, time, and radio buttons/checkboxes
    private void validateAllowableValues(FeatureType feature, List<String> allowableValues) throws InvalidAllowableValues {
        switch (feature) {
            case SCALE:
                if (allowableValues.size() != 2) throw new InvalidAllowableValues("A scale type must have a lower and upper bound.");
                break;
            case TIME:
                if (allowableValues.isEmpty() || allowableValues.size() > 3) throw new InvalidAllowableValues("One to three time measurements must be specified.");
                for (String value : allowableValues) {
                    if (!value.equals("days") && !value.equals("hours") && !value.equals("minutes")) {
                        throw new InvalidAllowableValues("Time measurements must be in days, hours, and/or minutes.");
                    }
                }
                break;
            // foregoes checking for unique inputs for now
            case CHECKBOX: case RADIOBTN:
                if (allowableValues.size() < 2) {
                    throw new InvalidAllowableValues("Two or more options must be provided.");
                }
                break;
        }
    }

    // NEED TO UPDATE THIS BIT
    public Feature update(Feature f) throws InvalidAllowableValues {
        Feature feature = featureRepo.findById(f.getFeatureId()).orElseThrow(() -> new EntityNotFoundException("Unable to find feature."));
        feature.setName(f.getName());
        feature.setType(f.getType());
        validateAllowableValues(f.getType(), f.getAllowableValues());
        feature.setAllowableValues(f.getAllowableValues());
        return featureRepo.save(feature);
    }

    public void delete(Integer featureId) {
        featureRepo.deleteById(featureId);
    }
}
