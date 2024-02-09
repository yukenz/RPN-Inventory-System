package com.awanrpn.invenmanager.mapper;

import com.awanrpn.invenmanager.model.dto.category.CategoryRequest;
import com.awanrpn.invenmanager.model.dto.category.CategoryResponse;
import com.awanrpn.invenmanager.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {

    Category createCategoryFromRequest(CategoryRequest categoryRequest);

    CategoryResponse categoryResponse(Category category);
}
