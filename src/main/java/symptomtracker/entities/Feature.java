package symptomtracker.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer featureId;

    @NotBlank(message = "Feature name is required.")
    @Size(max = 50, message = "Feature name must be 50 characters or less.")
    private String name;

    @NotNull(message = "Feature type is required.")
    private FeatureType type;

    @NotNull(message = "Feature allowable values are required.")
    private List<String> allowableValues;

    @ManyToOne
    @JsonBackReference(value = "symptom-feature")
    private Symptom symptom;

    @OneToMany(mappedBy = "feature", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "feature-instance")
    List<Instance> instances = new ArrayList<>();

    public Feature(Symptom symptom, String name, FeatureType type, List<String> allowableValues) {
        this.symptom = symptom;
        this.name = name;
        this.type = type;
        this.allowableValues = allowableValues;
    }
}
