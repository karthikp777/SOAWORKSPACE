package com.klef.ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.klef.ms.configuration.FeignConfig;
import com.klef.ms.dto.UserResponse;

@FeignClient(name = "UserService",
                          configuration = FeignConfig.class)   //Spring application name
public interface UserClient 
{   @GetMapping("/user/display/{id}")
    UserResponse getUserById(@PathVariable Long id);
}
