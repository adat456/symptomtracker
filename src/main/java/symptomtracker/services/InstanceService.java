package symptomtracker.services;

import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import symptomtracker.dtos.CreateInstanceDTO;
import symptomtracker.dtos.InstanceSliceDTO;
import symptomtracker.entities.Feature;
import symptomtracker.entities.Instance;
import symptomtracker.exceptions.InvalidAllowableValues;
import symptomtracker.repos.FeatureRepo;
import symptomtracker.repos.InstanceRepo;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstanceService {
    private final FeatureRepo featureRepo;
    private final InstanceRepo instanceRepo;

    public Instance get(Integer instanceId) {
        return instanceRepo.findById(instanceId).orElseThrow(() -> new EntityNotFoundException("Unable to find instance."));
    }

    public InstanceSliceDTO getSliceByFeature(Integer featureId, Integer pageNum, Integer pageSize, String sort) {
        Slice<Instance> slice = instanceRepo.findAllByFeatureId(featureId, PageRequest.of(pageNum, pageSize, sort.equals("descending") ? Sort.by("created").descending() : Sort.by("created").ascending()));
        return new InstanceSliceDTO(slice.getContent(), slice.hasNext() ? slice.nextPageable().getPageNumber() : null, pageSize, sort);
    }

    public Instance create(CreateInstanceDTO d) throws InvalidAllowableValues {
        Feature feature = featureRepo.findById(d.getFeatureId()).orElseThrow(() -> new EntityNotFoundException("Unable to find feature."));
        validateInstanceData(d.getData(), feature);
        return instanceRepo.save(new Instance(d.getData(), feature, d.getFeatureId()));
    }

    public Instance update(Instance d) throws InvalidAllowableValues {
        Instance instance = instanceRepo.findById(d.getInstanceId()).orElseThrow(() -> new EntityNotFoundException("Unable to find instance."));
        Feature feature = featureRepo.findById(d.getFeatureId()).orElseThrow(() -> new EntityNotFoundException("Unable to find feature."));
        validateInstanceData(d.getData(), feature);
        instance.setData(d.getData());
        return instanceRepo.save(instance);
    }

    private void validateInstanceData(List<String> data, Feature feature) throws InvalidAllowableValues {
        switch (feature.getType()) {
            case SCALE:
                // exactly one value required
                if (data.size() != 1) throw new InvalidAllowableValues("Scale data requires exactly one number.");
                // value must be an integer and fall within scale bounds
                if (Integer.parseInt(data.get(0)) < Integer.parseInt(feature.getAllowableValues().get(0)) || Integer.parseInt(data.get(0)) > Integer.parseInt(feature.getAllowableValues().get(1))) throw new InvalidAllowableValues("Scale data requires a number within bounds.");
                break;
            case TIME:
                // must be a value for each time measurement
                if (data.size() != feature.getAllowableValues().size()) throw new InvalidAllowableValues("Time data requires a value for each time measurement.");
                // all values must be integers
                for (String value : data) Integer.parseInt(value);
                break;
            case CHECKBOX:
                if (data.size() > feature.getAllowableValues().size()) throw new InvalidAllowableValues("Checkbox data must not exceed the number of allowable values.");
                for (String value : data) {
                    if (!feature.getAllowableValues().contains(value)) throw new InvalidAllowableValues("Checkbox data must be included in the allowable values.");
                }
                break;
            case RADIOBTN:
                // exactly one value required
                if (data.size() != 1) throw new InvalidAllowableValues("Radio button data requires exactly one value.");
                if (!feature.getAllowableValues().contains(data.get(0))) throw new InvalidAllowableValues("Radio button data must be one of the allowable values.");
                break;
            case BOOL:
                // exactly one value required
                if (data.size() != 1) throw new InvalidAllowableValues("Scale data requires exactly one T/F value.");
                // value must be either 'T' or 'F'
                if (!data.get(0).equals("T") && !data.get(0).equals("F")) throw new InvalidAllowableValues("Scale data must be either 'T' or 'F'.");
                break;
            case FREETEXT:
                if (data.get(0).length() > 2500) throw new InvalidAllowableValues("Free text data must not exceed 2500 characters.");
                break;
        }
    }

    public void delete(Integer instanceId) {
        instanceRepo.deleteById(instanceId);
    }

    public void deleteByFeature(Integer featureId) {
        instanceRepo.deleteByFeature(featureId);
    }
 }
