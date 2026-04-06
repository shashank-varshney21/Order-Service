package com.shashank.ecommerce.order_service.Entity;

import com.shashank.ecommerce.order_service.Enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class OrdersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Long customerId; //There will be a customer service that has customer table mapped with this id.

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private OrderItemsEntity item;
}
