package com.shashank.ecommerce.order_service.Dto;

import com.shashank.ecommerce.order_service.Entity.OrderItemsEntity;
import lombok.Data;
import java.util.List;

@Data
public class OrderRequestDto {
    private Long id;
    private List<OrderItemsEntity> items;
    private Double totalPrice;
}
