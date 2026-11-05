package com.klef.ms.service;

import java.util.List;

import com.klef.ms.dto.OrderRequest;
import com.klef.ms.dto.OrderResponse;
import com.klef.ms.entity.Order;

public interface OrderService 
{
    OrderResponse saveOrder(OrderRequest request);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long id);

    OrderResponse updateOrder(Long id, OrderRequest request);

    void deleteOrder(Long id);
    
    List<OrderResponse> displayOrdersByUserId(Long userid);
}
