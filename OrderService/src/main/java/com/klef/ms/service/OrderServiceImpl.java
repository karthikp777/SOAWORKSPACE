package com.klef.ms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.ms.client.ProductClient;
import com.klef.ms.client.UserClient;
import com.klef.ms.dto.OrderRequest;
import com.klef.ms.dto.OrderResponse;
import com.klef.ms.dto.ProductResponse;
import com.klef.ms.dto.UserResponse;
import com.klef.ms.entity.Order;
import com.klef.ms.exception.ResourceNotFoundException;
import com.klef.ms.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService 
{
    @Autowired
    private OrderRepository repository;
    
    private final UserClient userClient;
    
    private final ProductClient productClient;

    @Override
    public OrderResponse saveOrder(OrderRequest request) 
    {

    UserResponse user= userClient.getUserById(request.getUserId());
    
    ProductResponse product =productClient.getProductById(request.getProductId());
    
    Order order = Order.builder().userId(user.getId())
    		                     .productId(product.getId())
    		                     .quantity(request.getQuantity())
                                 .totalAmount(request.getQuantity()*product.getPrice())
                                 .build();
    
    
    Order savedorder = repository.save(order);
    
    return mapToResponse(savedorder);
    
    
    }

    @Override
    public List<OrderResponse> getAllOrders() 
    {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    
    @Override
    public OrderResponse getOrderById(Long id) 
    {
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id : " + id));

        return mapToResponse(order);
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderRequest request) 
    {
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id : " + id));

        UserResponse user = userClient.getUserById(request.getUserId());
        
        ProductResponse product = productClient.getProductById(request.getProductId());        
        
        order.setUserId(user.getId());
        order.setProductId(product.getId());
        order.setQuantity(request.getQuantity());
        order.setTotalAmount(request.getQuantity()*product.getPrice());

        Order updatedOrder = repository.save(order);

        return mapToResponse(updatedOrder);
    }

    @Override
    public void deleteOrder(Long id) 
    {
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id : " + id));

        repository.delete(order);
    }

    private OrderResponse mapToResponse(Order order) 
    {
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .totalAmount(order.getTotalAmount())
                .build();
    }

	@Override
	public List<OrderResponse> displayOrdersByUserId(Long userid) {
		
		
		 return repository.findByUserId(userid)
	                .stream()
	                .map(this::mapToResponse)
	                .collect(Collectors.toList());
	
	}

	
	
}
