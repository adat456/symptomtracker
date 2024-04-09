package symptomtracker.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

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

    @ManyToOne
    @JsonBackReference(value = "feature-instance")
    private Feature feature;
}
