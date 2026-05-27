package com.dio.warehouse.service;

import com.dio.warehouse.config.RabbitMQConfig;
import com.dio.warehouse.model.Product;
import com.dio.warehouse.repository.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StockUpdateListener {

    @Autowired
    private ProductRepository productRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void handleStockUpdate(Map<String, Object> message) {
        String productId = (String) message.get("productId");
        Integer quantityToSubtract = (Integer) message.get("quantity");

        productRepository.findById(productId).ifPresent(product -> {
            product.setQuantity(product.getQuantity() - quantityToSubtract);
            productRepository.save(product);
        });
    }
}
