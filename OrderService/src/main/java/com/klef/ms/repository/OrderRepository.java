package com.klef.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.ms.entity.Order;
import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> 
{
    List<Order> findByUserId(Long userId);
}
