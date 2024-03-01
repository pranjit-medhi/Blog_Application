package com.main.controllers;

import com.main.payloads.CategoryDto;
import com.main.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

//    getall Category
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>>  findAllCategory(){
        List<CategoryDto> allCategory = categoryService.getAllCategory();
        return  new ResponseEntity<>(allCategory, HttpStatus.OK);
    }
//    getSingle Category by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable Long id)
    {
        CategoryDto categoryById = categoryService.getCategoryById(id);
        return  new ResponseEntity<>(categoryById, HttpStatus.OK);
    }

//    create Category
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
    {
        CategoryDto category = categoryService.createCategory(categoryDto);
        return  new ResponseEntity<>(category, HttpStatus.OK);
    }

//    update
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long categoryId, @Valid @RequestBody CategoryDto categoryDto)
    {
        CategoryDto category = categoryService.updateCategory(categoryDto, categoryId);
        return  new ResponseEntity<>(category, HttpStatus.OK);
    }

    //    update
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long categoryId)
    {
        categoryService.deleteCategory(categoryId);
        return  new ResponseEntity<>( HttpStatus.OK);
    }

}
