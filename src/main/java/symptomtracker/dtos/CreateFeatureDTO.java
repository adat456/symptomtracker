package symptomtracker.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import symptomtracker.entities.FeatureType;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CreateFeatureDTO {
    @NotBlank(message = "Feature name is required.")
    @Size(max = 50, message = "Feature name must be 50 characters or less.")
    private final String name;

    @NotNull(message = "Feature type is required.")
    private final FeatureType type;

    @NotNull(message = "Feature allowable values are required.")
    private final List<String> allowableValues;

    @NotNull(message = "Symptom ID is required.")
    private final Integer symptomId;
}

