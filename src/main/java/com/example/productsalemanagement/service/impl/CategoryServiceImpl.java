package com.example.productsalemanagement.service.impl;

import com.example.productsalemanagement.dto.request.CategoryRequestDTO;
import com.example.productsalemanagement.entity.Category;
import com.example.productsalemanagement.exception.ConstraintViolationException;
import com.example.productsalemanagement.exception.ResourceNotFoundException;
import com.example.productsalemanagement.repository.CategoryRepository;
import com.example.productsalemanagement.repository.ProductRepository;
import com.example.productsalemanagement.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;


    @Override
    public List<Category> addCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = Category.builder()
                .categoryName(categoryRequestDTO.getName())
                .status(categoryRequestDTO.isStatus())
                .build();

        categoryRepository.save(category);
        return listCategory();
    }

    @Override
    public List<Category> deleteCategory(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        category.orElseThrow(() -> new ResourceNotFoundException());

        if(productRepository.findByCategory_Id(category.get().getId()).size() > 0)
            throw new ConstraintViolationException("Still have product belong to this category");

         categoryRepository.delete(category.get());

        return listCategory();
    }

    @Override
    public Category updateCategory(CategoryRequestDTO newCategory) {
        Optional<Category> category = categoryRepository.findById(newCategory.getId());
        category.orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        category.get().setName(newCategory.getName());
        category.get().setStatus(newCategory.isStatus());

        categoryRepository.save(category.get());

        return category.get();
    }

    @Override
    public List<Category> listCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        category.orElseThrow(() -> new ResourceNotFoundException("Not found category"));
        return category.get();
    }
}
