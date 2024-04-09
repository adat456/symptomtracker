package symptomtracker.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import symptomtracker.dtos.CreateCategoryDTO;
import symptomtracker.entities.Account;
import symptomtracker.entities.Category;
import symptomtracker.repos.AccountRepo;
import symptomtracker.repos.CategoryRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final AccountRepo accountRepo;
    private final CategoryRepo categoryRepo;

    public List<Category> getAll(Integer accountId) {
        return categoryRepo.findByAccountId(accountId);
    }

    public Category getOne(Integer categoryId) {
        return categoryRepo.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Unable to find category."));
    }

    public Category create(CreateCategoryDTO d) {
        Account account = accountRepo.findById(String.valueOf(d.getAccountId())).orElseThrow(() -> new EntityNotFoundException("No account found."));
        return categoryRepo.save(new Category(account, d.getName(), d.getDescription()));
    }

    public Category update(Category c) {
        Category category = categoryRepo.findById(c.getCategoryId()).orElseThrow(() -> new EntityNotFoundException("No category found."));
        category.setName(c.getName());
        category.setDescription(c.getDescription());
        return categoryRepo.save(category);
    }

    public String delete(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Unable to find category."));
        categoryRepo.deleteById(categoryId);
        return category.getName();
    }
}
