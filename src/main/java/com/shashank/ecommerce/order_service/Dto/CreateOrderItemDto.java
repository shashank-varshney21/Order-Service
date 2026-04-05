package com.shashank.ecommerce.order_service.Dto;

import lombok.Data;

@Data
public class CreateOrderItemDto {
    private Long productId;
    private Integer quantity;
}
