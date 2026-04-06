package com.shashank.ecommerce.order_service.Controllers;

import com.shashank.ecommerce.order_service.Dto.CreateOrderDto;
import com.shashank.ecommerce.order_service.Enums.OrderStatus;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import com.shashank.ecommerce.order_service.Dto.OrderRequestDto;
import com.shashank.ecommerce.order_service.Services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    @GetMapping
        public ResponseEntity<List<OrderRequestDto>> getAllOrders() {
            log.info("Fetching all orders via controller");
            List<OrderRequestDto> orders = orderService.getAllOrders();
            return ResponseEntity.ok(orders);
        }

    @GetMapping("/hello")
        public String helloFromOrderService() {
            return "hello from Order Service";
        }

    @GetMapping("/{id}")
        public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id) {
            log.info("Fetching order by id");
            OrderRequestDto order = orderService.getOrderById(id);
            return ResponseEntity.ok(order);
        }

    @GetMapping("/fetchFromInventoryService")
        public String fetchFromInventoryService() {
        List<ServiceInstance> instances = discoveryClient.getInstances("inventory-service");

        ServiceInstance inventoryService = instances.get(0);

        return restClient.get()
                .uri(inventoryService.getUri()+"/api/v1/products/helloInventory")
                .retrieve()
                .body(String.class);
        }

    @GetMapping("/customer/{customerId}")
        public List<OrderRequestDto> getCustomerOrders(@PathVariable Long customerId,@RequestParam int page, @RequestParam int size) {
        return orderService.getCustomerOrders(customerId, page, size);
    }

    @PostMapping("/create/order")
        public ResponseEntity<String> createOrder(@RequestBody CreateOrderDto orderDto) {
            return orderService.createOrder(orderDto);
    }

    @PatchMapping("/{id}/status/{status}")
        public ResponseEntity<String> updateOrderStatus(@PathVariable Long id, @PathVariable OrderStatus status) {
            return orderService.updateOrderStatus(id, status);
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
            return orderService.deleteOrder(id);
    }

}
