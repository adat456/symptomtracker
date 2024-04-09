package symptomtracker.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateCategoryDTO {
    @NotBlank(message = "Category name is required.")
    @Size(max = 50, message = "Category name must be 50 characters or less.")
    private final String name;

    @Size(max = 200, message = "Category description must be 50 characters or less.")
    private final String description;

    @NotNull(message = "Account ID is required.")
    private final Integer accountId;
}
