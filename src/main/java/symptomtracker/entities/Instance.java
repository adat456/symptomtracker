package symptomtracker.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Instance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer instanceId;

    @NotNull(message = "Instance data is required.")
    private List<String> data;

    @CreationTimestamp
    private Instant created;

    @UpdateTimestamp
    private Instant updated;

    @ManyToOne
    @JsonBackReference(value = "feature-instance")
    private Feature feature;

    @NotNull(message = "Feature ID is required.")
    private Integer featureId;

    public Instance(List<String> data, Feature feature, Integer featureId) {
        this.data = data;
        this.feature = feature;
        this.featureId = featureId;
    }
}
