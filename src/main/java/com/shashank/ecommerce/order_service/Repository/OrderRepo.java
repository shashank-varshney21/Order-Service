package com.shashank.ecommerce.order_service.Repository;

import com.shashank.ecommerce.order_service.Entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrdersEntity, Long> {
}
