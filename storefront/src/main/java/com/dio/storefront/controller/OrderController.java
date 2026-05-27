package com.dio.storefront.controller;

import com.dio.storefront.client.WarehouseClient;
import com.dio.storefront.model.Order;
import com.dio.storefront.repository.OrderRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WarehouseClient warehouseClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {
        // 1. Sincronicamente verifica o estoque via HTTP (Feign)
        Integer stock = warehouseClient.getStock(order.getProductId());

        if (stock != null && stock >= order.getQuantity()) {
            // Salva o pedido
            orderRepository.save(order);

            // 2. Assincronicamente notifica o warehouse para atualizar o estoque via RabbitMQ
            Map<String, Object> message = new HashMap<>();
            message.put("productId", order.getProductId());
            message.put("quantity", order.getQuantity());

            rabbitTemplate.convertAndSend("stock_exchange", "stock_routing_key", message);

            return ResponseEntity.status(HttpStatus.CREATED).body("Order placed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient stock");
        }
    }
}
