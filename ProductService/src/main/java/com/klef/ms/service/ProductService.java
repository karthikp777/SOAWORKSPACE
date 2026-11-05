package com.klef.ms.service;

import java.util.List;

import com.klef.ms.dto.ProductRequest;
import com.klef.ms.dto.ProductResponse;

public interface ProductService 
{
    ProductResponse saveProduct(ProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);
}
