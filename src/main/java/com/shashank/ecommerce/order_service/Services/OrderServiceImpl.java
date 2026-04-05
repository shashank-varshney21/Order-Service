package com.shashank.ecommerce.order_service.Services;

import com.shashank.ecommerce.order_service.Dto.CreateOrderDto;
import com.shashank.ecommerce.order_service.Dto.OrderRequestDto;
import com.shashank.ecommerce.order_service.Entity.OrdersEntity;
import com.shashank.ecommerce.order_service.Enums.OrderStatus;
import com.shashank.ecommerce.order_service.Repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Order;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final ModelMapper modelMapper;
    private final OrderRepo orderRepo;

    @Override
    public List<OrderRequestDto> getAllOrders() {
        List<OrdersEntity> orders = orderRepo.findAll();
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderRequestDto.class))
                .toList();
    }

    @Override
    public OrderRequestDto getOrderById(Long id) {
        Optional<OrdersEntity> order = orderRepo.findById(id);
        return order.map(item -> modelMapper.map(item, OrderRequestDto.class))
                .orElseThrow(()-> new RuntimeException("Inventory not found"));
    }

    @Override
    public ResponseEntity<String> createOrder(CreateOrderDto orderDto) {
        try{
            OrdersEntity order = modelMapper.map(orderDto, OrdersEntity.class);
            orderRepo.save(order);
        }catch(Exception e){
            ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
    }

    @Override
    public List<OrderRequestDto> getCustomerOrders(Long customerId, int page, int size) { //Here page index starts from 0.
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        Page<OrdersEntity> orders = orderRepo.findByCustomerId(customerId, pageable);

        return orders.getContent()
                .stream()
                .map(
                        o->{
                            return modelMapper.map(o, OrderRequestDto.class);
                        }
                )
                .toList();
    }

    @Override
    public ResponseEntity<String> updateOrderStatus(Long id, OrderStatus status) {
        Optional<OrdersEntity> order = orderRepo.findById(id);
        if(order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }
        OrdersEntity actualOrder = order.get();
        //You can make duplicate the actual order to maintain consistency
        actualOrder.setOrderStatus(status);
        orderRepo.save(actualOrder);
        return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
    }

    @Override
    public ResponseEntity<String> deleteOrder(Long id) {
        Optional<OrdersEntity> order = orderRepo.findById(id);
        if(order.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }
        OrdersEntity actualOrder = order.get();
        orderRepo.delete(actualOrder);
        return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
    }
}
