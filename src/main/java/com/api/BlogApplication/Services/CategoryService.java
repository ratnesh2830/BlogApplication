package com.api.BlogApplication.Services;

import com.api.BlogApplication.Entities.Category;
import com.api.BlogApplication.Payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(Category category);
    CategoryDto updateCategory(Category category,int categoryId);
    CategoryDto getSingleCategory(int categoryId);
    List<CategoryDto> getAllCategory();
    void delete(int categoryId);

}
