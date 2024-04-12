package symptomtracker.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CreateInstanceDTO {
    @NotNull(message = "Instance data is required.")
    private final List<String> data;

    @NotNull(message = "Feature ID is required.")
    private final Integer featureId;
}
