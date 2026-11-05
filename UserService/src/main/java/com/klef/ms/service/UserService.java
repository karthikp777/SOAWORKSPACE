package com.klef.ms.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.klef.ms.dto.LoginRequest;
import com.klef.ms.dto.OrderResponse;
import com.klef.ms.dto.ProductResponse;
import com.klef.ms.dto.UserRequest;
import com.klef.ms.dto.UserResponse;
@Service
public interface UserService 
{

    UserResponse saveUser(UserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UserRequest request);

    void deleteUser(Long id);
    
    
    //product service method
    List<ProductResponse> getAllProducts();
    
   //order service
    List<OrderResponse> dispalyOrdersByUserId(Long userid);
    
    
    UserResponse userLogin(LoginRequest request);
    
    
    UserDetails loadUserByUsername(String username);

	List<ProductResponse> displayAllProducts();

	List<OrderResponse> displayOrdersByUserId(Long userid);
}