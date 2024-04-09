package symptomtracker.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateInstanceDTO {
    @Column(columnDefinition = "jsonb")
    private final String featureData;

    @NotNull(message = "Symptom ID is required.")
    private final Integer symptomId;
}
