package symptomtracker.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Symptom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer symptomId;

    @NotBlank(message = "Symptom name is required.")
    @Size(max = 50, message = "Symptom name must be 50 characters or less.")
    private String name;

    @Size(max = 200, message = "Symptom description must be 50 characters or less.")
    private String description;

    @CreationTimestamp
    private Instant created;

    @UpdateTimestamp
    private Instant updated;

    @ManyToOne
    @JsonBackReference(value = "account-symptom")
    private Account account;

    @ManyToOne
    @JsonBackReference(value = "category-symptom")
    private Category category;

    @OneToMany(mappedBy = "symptom", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "symptom-feature")
    List<Feature> features = new ArrayList<>();

    public Symptom(Account account, Category category, String name, String description) {
        this.account = account;
        this.category = category;
        this.name = name;
        this.description = description;
    }
}
