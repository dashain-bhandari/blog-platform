package in.dash.blog_platform.controllers;

import in.dash.blog_platform.dtos.CreateCategoryRequest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.dash.blog_platform.dtos.CategoryDto;
import in.dash.blog_platform.entities.Category;
import in.dash.blog_platform.mappers.CategoryMapper;
import in.dash.blog_platform.services.CategoryService;

import java.util.*;

@RestController()
@RequestMapping(path = "/api/v1/categories")

public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryMapper mapper) {
        super();
        this.categoryService = categoryService;
        this.categoryMapper = mapper;
    }


    @GetMapping("")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = this.categoryService.getAllCategories()
                .stream()
                .map(category -> this.categoryMapper.toDto(category))
                .toList();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody() CreateCategoryRequest createCategoryRequest) {
        Category category = this.categoryMapper.toEntity(createCategoryRequest);

        Category createdCategory = this.categoryService.createCategory(category);
        CategoryDto dto = this.categoryMapper.toDto(createdCategory);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseBody()
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") UUID id) {

        this.categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
