package com.awanrpn.invenmanager.service;

import com.awanrpn.invenmanager.mapper.ObjAutoMapper;
import com.awanrpn.invenmanager.model.entity.Category;
import com.awanrpn.invenmanager.model.entity.Product;
import com.awanrpn.invenmanager.model.entity.User;
import com.awanrpn.invenmanager.model.request.CreateProductRequest;
import com.awanrpn.invenmanager.model.request.UpdateProductRequest;
import com.awanrpn.invenmanager.model.response.CreateProductResponse;
import com.awanrpn.invenmanager.model.response.GetProductResponse;
import com.awanrpn.invenmanager.model.response.UpdateProductResponse;
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
    private final ObjAutoMapper objAutoMapper;


    @Transactional(timeout = 2)
    public CreateProductResponse
    createProduct(CreateProductRequest createProductRequest) {

        Product newProduct = objAutoMapper.createProductFromRequest(createProductRequest);
        Product product = productRepository.save(newProduct);

        return objAutoMapper.createProductResponse(product);

    }

    @Transactional(timeout = 2, readOnly = true)
    public List<GetProductResponse>
    getAllProducts() {

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(objAutoMapper::getProductResponse)
                .toList();
    }

    @Transactional(timeout = 2, readOnly = true)
    public GetProductResponse
    getProductById(
            String uuid
    ) {
        Product product = productRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("ID Product tidak ditemukan"));

        return objAutoMapper.getProductResponse(product);
    }

    @Transactional(timeout = 2)
    public UpdateProductResponse
    updateProductById(
            String uuid,
            UpdateProductRequest updateProductRequest
    ) {

        Product product = productRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("ID Product tidak ditemukan"));

        String name = updateProductRequest.name();
        String description = updateProductRequest.description();
        BigInteger price = updateProductRequest.price();
        Integer quantityInStock = updateProductRequest.quantityInStock();
        String category_uuid = updateProductRequest.category_uuid();
        String user_uuid = updateProductRequest.user_uuid();

        Category category = categoryRepository.findById(category_uuid)
                .orElse(null);

        User user = userRepository.findById(user_uuid)
                .orElseThrow(() -> new IllegalArgumentException("User dengan id tersebut tidak ada"));

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantityInStock(quantityInStock);
        product.setCategory(category);
        product.setUser(user);

        return objAutoMapper.updateProductResponse(product);
    }

    @Transactional(timeout = 2)
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
