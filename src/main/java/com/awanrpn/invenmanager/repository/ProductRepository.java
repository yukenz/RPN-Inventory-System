package com.awanrpn.invenmanager.repository;

import com.awanrpn.invenmanager.model.entity.Product;
import com.awanrpn.invenmanager.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findAllByUser(User user);

}
