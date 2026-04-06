package com.shashank.ecommerce.order_service.Repository;

import com.shashank.ecommerce.order_service.Entity.OrdersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

public interface OrderRepo extends JpaRepository<OrdersEntity, Long> {
    Page<OrdersEntity> findByCustomerId(Long customerId, Pageable pageable);
}