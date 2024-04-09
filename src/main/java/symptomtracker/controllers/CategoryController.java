package symptomtracker.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import symptomtracker.dtos.CreateCategoryDTO;
import symptomtracker.entities.Category;
import symptomtracker.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Category>> getAll(@PathVariable("accountId") Integer accountId) {
        return ResponseEntity.ok(categoryService.getAll(accountId));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getOne(@PathVariable("categoryId") Integer categoryId) {
        return ResponseEntity.ok(categoryService.getOne(categoryId));
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody @Valid CreateCategoryDTO d) {
        return ResponseEntity.ok(categoryService.create(d));
    }

    @PutMapping
    public ResponseEntity<Category> update(@RequestBody @Valid Category c) {
        return ResponseEntity.ok(categoryService.update(c));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete (@PathVariable("id") Integer categoryId) {
        return ResponseEntity.ok(categoryService.delete(categoryId) + " category was deleted.");
    }
}
