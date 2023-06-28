package com.api.BlogApplication.Services.Implementation;

import com.api.BlogApplication.Entities.Category;
import com.api.BlogApplication.Exceptions.ResourceNotFoundException;
import com.api.BlogApplication.Payloads.CategoryDto;
import com.api.BlogApplication.Repositories.CategoryRepo;
import com.api.BlogApplication.Services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImple implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(Category category)
    {

        Category savedCategory=categoryRepo.save(category);

        CategoryDto categoryDto = categoryToCategoryDto(savedCategory);
        return categoryDto;
    }

    @Override
    public CategoryDto updateCategory(Category category, int categoryId)
    {
        Category category1 = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));

        category1.setCategoryTitle(category.getCategoryTitle());
        category1.setCategoryDescription(category.getCategoryDescription());
        Category category2 = categoryRepo.save(category1);

        CategoryDto categoryDto = categoryToCategoryDto(category2);

        return categoryDto;
    }

    @Override
    public CategoryDto getSingleCategory(int categoryId)
    {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));

        CategoryDto categoryDto = categoryToCategoryDto(category);

        return  categoryDto;
    }

    @Override
    public List<CategoryDto> getAllCategory()
    {
        Iterable<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for(Category category:categories)
        {
            CategoryDto categoryDto = categoryToCategoryDto(category);
            categoryDtos.add(categoryDto);
        }
        return  categoryDtos;
    }

    @Override
    public void delete(int categoryId)
    {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));

        categoryRepo.delete(category);
    }

    public CategoryDto categoryToCategoryDto(Category category)
    {
        CategoryDto categoryDto = modelMapper.map(category,CategoryDto.class);
        return categoryDto;
    }
}
