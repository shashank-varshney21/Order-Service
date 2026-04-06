package com.shashank.ecommerce.order_service.Dto;

import com.shashank.ecommerce.order_service.Enums.OrderStatus;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDto {
    @Id
    private Long id;
    private Double totalPrice;
    private Long customerId;
    private OrderStatus orderStatus;
    private CreateOrderItemDto createOrderItemDto;
}
