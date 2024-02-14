package com.awanrpn.invenmanager.service;

import com.awanrpn.invenmanager.mapper.DTOMapper;
import com.awanrpn.invenmanager.mapper.ProductMapper;
import com.awanrpn.invenmanager.model.dto.product.*;
import com.awanrpn.invenmanager.model.entity.Category;
import com.awanrpn.invenmanager.model.entity.Product;
import com.awanrpn.invenmanager.model.entity.User;
import com.awanrpn.invenmanager.repository.CategoryRepository;
import com.awanrpn.invenmanager.repository.ProductRepository;
import com.awanrpn.invenmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper = DTOMapper.PRODUCT_MAPPER;


    @Transactional()
    public CreateProductResponse
    createProduct(CreateProductRequest createProductRequest, String user_uuid) {

        User user = userRepository.findById(user_uuid)
                .orElseThrow(() -> new IllegalArgumentException("ID User tidak ditemukan"));

        Product productPreSave = productMapper.createProductFromRequest(createProductRequest);
        productPreSave.setUser(user);

        Product productSaved = productRepository.save(productPreSave);
        return productMapper.createProductResponse(productSaved);

    }

    @Transactional(readOnly = true)
    public List<GetProductResponse>
    getAllProducts() {

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(productMapper::getProductResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public GetProductResponse
    getProductById(
            String uuid
    ) {
        Product product = productRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("ID Product tidak ditemukan"));

        return productMapper.getProductResponse(product);
    }

    @Transactional()
    public UpdateProductResponse
    updateProductById(
            String uuid,
            UpdateProductRequest updateProductRequest
    ) {

        Product productPreSave = productRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("ID Product tidak ditemukan"));

        String name = updateProductRequest.name();
        String description = updateProductRequest.description();
        BigInteger price = updateProductRequest.price();
        Integer quantityInStock = updateProductRequest.quantityInStock();
        String category_uuid = updateProductRequest.category_uuid();
        String user_uuid = updateProductRequest.user_uuid();

        Category category = categoryRepository.findById(category_uuid)
                .orElseThrow(() -> new IllegalArgumentException("Category dengan id tersebut tidak ada"));

        User user = userRepository.findById(user_uuid)
                .orElseThrow(() -> new IllegalArgumentException("User dengan id tersebut tidak ada"));

        productPreSave.setName(name);
        productPreSave.setDescription(description);
        productPreSave.setPrice(price);
        productPreSave.setQuantityInStock(quantityInStock);

        productPreSave.setCategory(category);
        productPreSave.setUser(user);

        Product productSaved = productRepository.save(productPreSave);
        return productMapper.updateProductResponse(productSaved);
    }

    @Transactional()
    public Boolean deleteProduct(String uuid) {

        try {
            if (!productRepository.existsById(uuid)) {
                throw new IllegalArgumentException("User Id tidak ditemukan");
            }
            productRepository.deleteById(uuid);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        return true;
    }

}
