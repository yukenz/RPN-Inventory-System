package com.awanrpn.invenmanager.controller;

import com.awanrpn.invenmanager.model.dto.product.CreateProductRequest;
import com.awanrpn.invenmanager.model.dto.product.UpdateProductRequest;
import com.awanrpn.invenmanager.model.dto.product.CreateProductResponse;
import com.awanrpn.invenmanager.model.dto.product.GetProductResponse;
import com.awanrpn.invenmanager.model.dto.ResponsePayloadBuilder;
import com.awanrpn.invenmanager.model.dto.product.UpdateProductResponse;
import com.awanrpn.invenmanager.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/products")
@RequiredArgsConstructor
@Tag(name = "Product Module")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Create Product")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    createProduct(
            @RequestBody CreateProductRequest createProductRequest
    ) {

        CreateProductResponse createProductResponse = productService.createProduct(createProductRequest);
        return ResponsePayloadBuilder.ok(createProductResponse);
    }

    @Operation(summary = "Show all products")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    getAllProducts() {

        List<GetProductResponse> products = productService.getAllProducts();
        return ResponsePayloadBuilder.ok(products);
    }

    @Operation(summary = "Get Product by ID")
    @GetMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    getProductById(
            @PathVariable(name = "productId") String uuid
    ) {
        GetProductResponse productById = productService.getProductById(uuid);
        return ResponsePayloadBuilder.ok(productById);
    }

    @Operation(summary = "Update Product by ID")
    @PutMapping(path = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    updateProductById(
            @PathVariable(name = "productId") String uuid,
            @RequestBody UpdateProductRequest updateProductRequest
    ) {
        UpdateProductResponse updateProductResponse = productService.updateProductById(uuid, updateProductRequest);
        return ResponsePayloadBuilder.ok(updateProductResponse);
    }

    @Operation(summary = "Delete Product by ID")
    @DeleteMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    deleteProduct(
            @PathVariable(name = "productId") String uuid
    ) {

        Boolean isSuccess = productService.deleteProduct(uuid);
        return ResponsePayloadBuilder.ok(isSuccess);
    }


}
