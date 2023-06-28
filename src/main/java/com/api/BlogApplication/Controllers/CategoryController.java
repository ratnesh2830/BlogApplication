package com.api.BlogApplication.Controllers;

import com.api.BlogApplication.Entities.Category;
import com.api.BlogApplication.Payloads.CategoryDto;
import com.api.BlogApplication.Services.CategoryService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody Category category)
    {
        CategoryDto categoryDto = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
    }

    //update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody Category category,@PathVariable int categoryId)
    {
        CategoryDto updatedCategory = categoryService.updateCategory(category,categoryId);
        return ResponseEntity.ok().body(updatedCategory);
    }

    //delete
    @DeleteMapping("/{categoryId}")
    public  ResponseEntity<String> deleteCategory(@PathVariable int categoryId)
    {
        categoryService.delete(categoryId);
        return ResponseEntity.ok("Successfully Deleted");
    }

    //getsingle
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable int categoryId)
    {
        CategoryDto categoryDto = categoryService.getSingleCategory(categoryId);

        return ResponseEntity.ok().body(categoryDto);
    }

    //getall
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories()
    {
        List<CategoryDto> categories = categoryService.getAllCategory();

        return ResponseEntity.ok().body(categories);
    }
}
