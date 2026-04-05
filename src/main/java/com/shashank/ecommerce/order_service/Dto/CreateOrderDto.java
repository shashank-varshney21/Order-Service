package com.shashank.ecommerce.order_service.Dto;

import com.shashank.ecommerce.order_service.Enums.OrderStatus;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDto {
    private Double totalPrice;
    private Long customerId;
    private OrderStatus orderStatus;
    private List<CreateOrderItemDto> createOrderItemDto;
}
