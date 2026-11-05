package com.klef.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.ms.dto.OrderRequest;
import com.klef.ms.dto.OrderResponse;
import com.klef.ms.service.OrderService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/order")
public class OrderController 
{
    @Autowired
    private OrderService service;

    @GetMapping("/")
    public String home()
    {
        return "Order Service Project";
    }

    @PostMapping("/add")
    public ResponseEntity<OrderResponse> saveOrder(@Valid @RequestBody OrderRequest request) 
    {
        return new ResponseEntity<>(service.saveOrder(request), HttpStatus.CREATED);
    }

    @GetMapping("displayall")
    public ResponseEntity<List<OrderResponse>> getAllOrders() 
    {
        return ResponseEntity.ok(service.getAllOrders());
    }

    @GetMapping("display/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) 
    {
        return ResponseEntity.ok(service.getOrderById(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderRequest request) 
    {
        return ResponseEntity.ok(service.updateOrder(id, request));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) 
    {
        service.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully.");
    }
    
    @GetMapping("displaybyuserid")
    public ResponseEntity<List<OrderResponse>> getOrdersByUserId(@RequestParam Long userid) 
    {
        return ResponseEntity.ok(service.displayOrdersByUserId(userid));
    }
}
