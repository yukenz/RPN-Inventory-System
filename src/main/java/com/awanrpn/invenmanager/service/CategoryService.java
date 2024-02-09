package com.awanrpn.invenmanager.service;

import com.awanrpn.invenmanager.mapper.CategoryMapper;
import com.awanrpn.invenmanager.mapper.DTOMapper;
import com.awanrpn.invenmanager.model.entity.Category;
import com.awanrpn.invenmanager.model.dto.category.CategoryRequest;
import com.awanrpn.invenmanager.model.dto.category.CategoryResponse;
import com.awanrpn.invenmanager.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper = DTOMapper.CATEGORY_MAPPER;

    @Transactional(timeout = 2)
    public CategoryResponse
    createCategory(
            CategoryRequest categoryRequest
    ) {

        Category category = categoryMapper.createCategoryFromRequest(categoryRequest);
        Category categoryInDB = categoryRepository.save(category);

        return categoryMapper.categoryResponse(categoryInDB);
    }

    @Transactional(timeout = 2, readOnly = true)
    public List<CategoryResponse>
    getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(categoryMapper::categoryResponse).toList();
    }

    @Transactional(timeout = 2, readOnly = true)
    public CategoryResponse
    getCategoryById(String uuid) {
        Category category = categoryRepository.findById(uuid)
                .orElseThrow((() -> new IllegalArgumentException("User Id tidak ditemukan")));

        return categoryMapper.categoryResponse(category);
    }

    @Transactional(timeout = 2)
    public CategoryResponse
    updateCategoryById(String uuid, CategoryRequest categoryRequest) {

        Category category = categoryRepository.findById(uuid)
                .orElseThrow((() -> new IllegalArgumentException("User Id tidak ditemukan")));

        category.setName(categoryRequest.name());
        categoryRepository.save(category);

        return categoryMapper.categoryResponse(category);
    }

    @Transactional(timeout = 2)
    public Boolean
    deleteCategoryById(String uuid) {

        try {
            if (!categoryRepository.existsById(uuid)) {
                throw new IllegalArgumentException("User Id tidak ditemukan");
            }
            categoryRepository.deleteById(uuid);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        return true;
    }
}
