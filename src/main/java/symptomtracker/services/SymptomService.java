package symptomtracker.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import symptomtracker.dtos.CreateSymptomDTO;
import symptomtracker.entities.Account;
import symptomtracker.entities.Category;
import symptomtracker.entities.Symptom;
import symptomtracker.repos.AccountRepo;
import symptomtracker.repos.CategoryRepo;
import symptomtracker.repos.SymptomRepo;

@Service
@RequiredArgsConstructor
public class SymptomService {
    private final AccountRepo accountRepo;
    private final CategoryRepo categoryRepo;
    private final SymptomRepo symptomRepo;

    public Symptom create(CreateSymptomDTO d) {
        Account account = accountRepo.findById(String.valueOf(d.getAccountId())).orElseThrow(() -> new EntityNotFoundException("Unable to find account."));
        Category category =
                d.getCategoryId() != null ?
                        categoryRepo.findById(d.getCategoryId()).orElseThrow(() -> new EntityNotFoundException("Unable to find category.")) :
                        null;
        return symptomRepo.save(new Symptom(account, category, d.getName(), d.getDescription()));
    }

    public Symptom updateNameDescription(Symptom s) {
        Symptom symptom = symptomRepo.findById(s.getSymptomId()).orElseThrow(() -> new EntityNotFoundException("Unable to find symptom."));
        symptom.setName(s.getName());
        symptom.setDescription(s.getDescription());
        return symptomRepo.save(symptom);
    }

    public Symptom updateCategory(Integer symptomId, Integer categoryId) {
        Symptom symptom = symptomRepo.findById(symptomId).orElseThrow(() -> new EntityNotFoundException("Unable to find symptom."));
        if (categoryId != 0) {
            Category newCategory = categoryRepo.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Unable to find category."));
            symptom.setCategory(newCategory);
        } else {
            symptom.setCategory(null);
        }
        return symptomRepo.save(symptom);
    }

    public void delete(Integer symptomId) {
        symptomRepo.deleteById(symptomId);
    }
}
