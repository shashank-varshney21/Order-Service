package com.shashank.ecommerce.order_service.Services;

import com.shashank.ecommerce.order_service.Dto.OrderRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrderService {
    public List<OrderRequestDto> getAllOrders();

    public OrderRequestDto getOrderById(Long id);
}
