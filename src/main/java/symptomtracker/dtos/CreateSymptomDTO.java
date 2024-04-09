package symptomtracker.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class CreateSymptomDTO {
    @NotBlank(message = "Symptom name is required.")
    @Size(max = 50, message = "Symptom name must be 50 characters or less.")
    private final String name;

    @Size(max = 200, message = "Symptom description must be 50 characters or less.")
    private final String description;

    @NotNull(message = "Account ID is required.")
    private final Integer accountId;

    @NotNull(message = "Category ID is required.")
    private final Integer categoryId;

    // note that there's no need to specify the list of owned entities in the DTO; this list has already been initialized in the original entity
}
