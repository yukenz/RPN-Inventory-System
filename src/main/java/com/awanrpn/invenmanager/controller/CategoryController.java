package com.awanrpn.invenmanager.controller;

import com.awanrpn.invenmanager.model.request.CategoryRequest;
import com.awanrpn.invenmanager.model.request.CategoryResponse;
import com.awanrpn.invenmanager.model.response.ResponsePayloadBuilder;
import com.awanrpn.invenmanager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    createCategory(
            @RequestBody CategoryRequest categoryRequest
    ) {

        CategoryResponse categoryServiceCategory = categoryService.createCategory(categoryRequest);
        return ResponsePayloadBuilder.ok(categoryServiceCategory);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    getAllCategories() {

        List<CategoryResponse> categoriesResponse = categoryService.getAllCategories();
        return ResponsePayloadBuilder.ok(categoriesResponse);

    }

    @GetMapping(path = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    getCategoryById(
            @PathVariable(name = "categoryId") String uuid
    ) {

        CategoryResponse categoryById = categoryService.getCategoryById(uuid);
        return ResponsePayloadBuilder.ok(categoryById);

    }

    @PutMapping(path = "/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    updateCategoryById(
            @PathVariable(name = "categoryId") String uuid,
            @RequestBody CategoryRequest categoryRequest
    ) {

        CategoryResponse categoryById = categoryService.updateCategoryById(uuid, categoryRequest);
        return ResponsePayloadBuilder.ok(categoryById);

    }

    @DeleteMapping(path = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    deleteCategoryById(
            @PathVariable(name = "categoryId") String uuid
    ) {

        Boolean isSuccess = categoryService.deleteCategoryById(uuid);
        return ResponsePayloadBuilder.ok(isSuccess);

    }

}
