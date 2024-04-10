package symptomtracker.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Instance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer instanceId;

    @CreatedDate
    private Date createdDate;

    @NotNull(message = "Instance must contain data.")
    private List<String> featureData;

    @CreationTimestamp
    private Instant created;

    @UpdateTimestamp
    private Instant updated;

    @ManyToOne
    @JsonBackReference(value = "feature-instance")
    private Feature feature;
}
