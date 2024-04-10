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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryId;

    @NotBlank(message = "Category name is required.")
    @Size(max = 50, message = "Category name must be 50 characters or less.")
    private String name;

    @Size(max = 200, message = "Category description must be 50 characters or less.")
    private String description;

    @CreationTimestamp
    private Instant created;

    @UpdateTimestamp
    private Instant updated;

    @ManyToOne
    @JsonBackReference(value = "account-category")
    private Account account;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "category-symptom")
    List<Symptom> symptoms = new ArrayList<>();

    public Category(Account account, String name, String description) {
        this.account = account;
        this.name = name;
        this.description = description;
    }
}
