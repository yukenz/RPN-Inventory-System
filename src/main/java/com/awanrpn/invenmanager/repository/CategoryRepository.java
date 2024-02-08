package com.awanrpn.invenmanager.repository;

import com.awanrpn.invenmanager.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
