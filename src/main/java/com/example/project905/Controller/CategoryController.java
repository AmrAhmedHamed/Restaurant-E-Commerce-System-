package com.example.project905.Controller;

import com.example.project905.Dto.CategoryDto;
import com.example.project905.Service.CategorySerice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/CategoryController")
@Tag(name = "Category Management", description = "APIs for managing categories")
public class CategoryController {

    private CategorySerice categoryService;

    @Autowired
    public CategoryController(CategorySerice categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Get all categories", description = "Retrieve all categories")
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @Operation(summary = "Create category", description = "Create a new category")
    @PostMapping("/save")
    public ResponseEntity<CategoryDto> save(@Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.save(categoryDto));
    }

    @Operation(summary = "Create multiple categories", description = "Create multiple categories at once")
    @PostMapping("/saveAll")
    public ResponseEntity<List<CategoryDto>> saveAll(@Valid @RequestBody List<CategoryDto> categoryDtos) {
        return ResponseEntity.ok(categoryService.saveList(categoryDtos));
    }

    @Operation(summary = "Update category", description = "Update an existing category")
    @PutMapping("/update")
    public ResponseEntity<CategoryDto> update(@Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.update(categoryDto));
    }

    @Operation(summary = "Get categories ordered by name", description = "Get all categories ordered by name ascending")
    @GetMapping("/orderByName")
    public ResponseEntity<List<CategoryDto>> getCategoriesByNameAsc() {
        return ResponseEntity.ok(categoryService.getAllByNameAsc());
    }

    @Operation(summary = "Get categories ordered by ID", description = "Get all categories ordered by ID ascending")
    @GetMapping("/orderById")
    public ResponseEntity<List<CategoryDto>> getCategoriesByIdAsc() {
        return ResponseEntity.ok(categoryService.getAllByIdAsc());
    }

    @Operation(summary = "Delete category", description = "Delete a category by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "Category ID") @PathVariable Long id) {
        boolean deleted = categoryService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}