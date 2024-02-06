package com.awanrpn.invenmanager.controller;

import com.awanrpn.invenmanager.model.request.CreateProductRequest;
import com.awanrpn.invenmanager.model.response.CreateProductResponse;
import com.awanrpn.invenmanager.model.response.GetProductResponse;
import com.awanrpn.invenmanager.model.response.ResponsePayloadBuilder;
import com.awanrpn.invenmanager.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/products")
@RequiredArgsConstructor
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
    @GetMapping()
    public ResponseEntity<?>
    getAllProducts() {

        List<GetProductResponse> products = productService.getAllProducts();
        return ResponsePayloadBuilder.ok(products);
    }

    @Operation(summary = "")
    @GetMapping(path = "/{productId}")
    public ResponseEntity<?>
    getProductById(
            @PathVariable(name = "productId") String uuid
    ) {
        return ResponsePayloadBuilder.ok(null);
    }

    @Operation(summary = "")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProductById() {
        return ResponsePayloadBuilder.ok(null);
    }

    @Operation(summary = "")
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProduct() {
        return ResponsePayloadBuilder.ok(null);
    }


}
