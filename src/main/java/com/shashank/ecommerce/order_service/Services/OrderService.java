package com.shashank.ecommerce.order_service.Services;

import com.shashank.ecommerce.order_service.Dto.CreateOrderDto;
import com.shashank.ecommerce.order_service.Dto.OrderRequestDto;
import com.shashank.ecommerce.order_service.Enums.OrderStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrderService {
    List<OrderRequestDto> getAllOrders();

    OrderRequestDto getOrderById(Long id);

    ResponseEntity<String> createOrder(CreateOrderDto orderDto);

    List<OrderRequestDto> getCustomerOrders(Long customerId, int page, int size);

    ResponseEntity<String> updateOrderStatus(Long id, OrderStatus status);

    ResponseEntity<String> deleteOrder(Long id);
}
