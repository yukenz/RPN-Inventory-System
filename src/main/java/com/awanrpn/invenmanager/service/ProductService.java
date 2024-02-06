package com.awanrpn.invenmanager.service;

import com.awanrpn.invenmanager.config.ObjAutoMapper;
import com.awanrpn.invenmanager.model.entity.Product;
import com.awanrpn.invenmanager.model.request.CreateProductRequest;
import com.awanrpn.invenmanager.model.response.CreateProductResponse;
import com.awanrpn.invenmanager.model.response.GetProductResponse;
import com.awanrpn.invenmanager.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ObjAutoMapper objAutoMapper;

    @Transactional(timeout = 2)
    public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {

        Product newProduct = objAutoMapper.createProductFromRequest(createProductRequest);
        Product product = productRepository.save(newProduct);

        return objAutoMapper.createProductResponse(product);

    }

    @Transactional(timeout = 2, readOnly = true)
    public List<GetProductResponse> getAllProducts() {

        List<Product> products = productRepository.findAll();

        List<GetProductResponse> allProducts
                = products.stream().map(product -> new GetProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantityInStock(),
                product.getCategory(),
                product.getUser().getId(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        )).toList();

        return allProducts;
    }

}
