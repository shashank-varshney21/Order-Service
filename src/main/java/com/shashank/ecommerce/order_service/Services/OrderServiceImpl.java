package com.shashank.ecommerce.order_service.Services;

import com.shashank.ecommerce.order_service.Dto.OrderRequestDto;
import com.shashank.ecommerce.order_service.Entity.OrdersEntity;
import com.shashank.ecommerce.order_service.Repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final ModelMapper modelMapper;
    private final OrderRepo orderRepo;

    public List<OrderRequestDto> getAllOrders() {
        List<OrdersEntity> orders = orderRepo.findAll();
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderRequestDto.class))
                .toList();
    }

    public OrderRequestDto getOrderById(Long id) {
        Optional<OrdersEntity> order = orderRepo.findById(id);
        return order.map(item -> modelMapper.map(item, OrderRequestDto.class))
                .orElseThrow(()-> new RuntimeException("Inventory not found"));
    }
}
