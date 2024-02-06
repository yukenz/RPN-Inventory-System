package com.awanrpn.invenmanager.repository;

import com.awanrpn.invenmanager.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
