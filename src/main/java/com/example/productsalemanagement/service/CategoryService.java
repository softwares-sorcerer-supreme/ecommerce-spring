package com.example.productsalemanagement.service;


import com.example.productsalemanagement.dto.request.CategoryRequestDTO;
import com.example.productsalemanagement.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> addCategory(CategoryRequestDTO category);
    List<Category> deleteCategory(int id);
    Category updateCategory(CategoryRequestDTO category);
    List<Category> listCategory();
    Category getCategory(int id);


}
