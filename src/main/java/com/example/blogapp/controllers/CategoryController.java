package com.example.blogapp.controllers;

import com.example.blogapp.payloads.ApiResponse;
import com.example.blogapp.payloads.CategoryDto;
import com.example.blogapp.service.implementations.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createdCategory,HttpStatus.CREATED);
    }

    @PutMapping("/{category_id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer category_id){
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, category_id);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{category_id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer category_id){
        categoryService.deleteCategory(category_id);
        return new ResponseEntity<>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer category_id){
        CategoryDto categoryDto = categoryService.getCategory(category_id);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategory(){
        List<CategoryDto> categoryDtoList = categoryService.getAllCategories()  ;
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);

    }


}
