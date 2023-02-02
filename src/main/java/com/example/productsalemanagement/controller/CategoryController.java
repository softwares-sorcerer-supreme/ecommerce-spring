package com.example.productsalemanagement.controller;

import com.example.productsalemanagement.dto.request.CategoryRequestDTO;
import com.example.productsalemanagement.entity.Category;
import com.example.productsalemanagement.service.CategoryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> listCategory() {
        return new ResponseEntity<>(categoryService.listCategory(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Category>> addCategory(@RequestBody @Valid CategoryRequestDTO categoryRequestDTO) {
        return new ResponseEntity<>(categoryService.addCategory(categoryRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable int id) {
        return new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody CategoryRequestDTO category) {
        return new ResponseEntity<>(categoryService.updateCategory(category), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Category>> deleteCategory(@PathVariable int id) {
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
    }


}
