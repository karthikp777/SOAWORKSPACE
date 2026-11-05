package com.klef.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.ms.dto.ProductRequest;
import com.klef.ms.dto.ProductResponse;
import com.klef.ms.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/product")
public class ProductController 
{
    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String home()
    {
        return "Product Service Project";
    }
    
    @Value("${server.port}")
    private String port;

    @GetMapping("/instance")
    public String instance()
    {
    return "Product Service instance running on port: " + port;
    }

    @Operation(
    		summary = "Add Product",
    		description = "Adding the a Product with Category, Name, Price, and Quantity "
    		)
    @PostMapping("/add")
    public ResponseEntity<ProductResponse> saveProduct(@Valid @RequestBody ProductRequest request) 
    {
        return new ResponseEntity<>(service.saveProduct(request), HttpStatus.CREATED);
    }

    @GetMapping("displayall")
    public ResponseEntity<List<ProductResponse>> getAllProducts() 
    {   
      	System.out.println("Product Service instance running on port: " + port);

        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("display/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) 
    {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest request) 
    {
        return ResponseEntity.ok(service.updateProduct(id, request));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) 
    {
        service.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully.");
    }
}
