package symptomtracker.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountId;

    @NotBlank(message = "Username is required.")
    @Size(min = 8, max = 30, message = "Username must be between 8 and 30 characters.")
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.")
    private String password;
    // https://stackoverflow.com/questions/19605150/regex-for-password-must-contain-at-least-eight-characters-at-least-one-number-a

    @Email(message = "Email address must have a valid format.")
    private String email;

    @CreatedDate
    private Date createdDate;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "account-category")
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "account-symptom")
    private List<Symptom> symptoms = new ArrayList<>();

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
