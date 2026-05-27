package com.dio.warehouse.controller;

import com.dio.warehouse.model.Product;
import com.dio.warehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Integer> getStock(@PathVariable String id) {
        return productRepository.findById(id)
                .map(product -> ResponseEntity.ok(product.getQuantity()))
                .orElse(ResponseEntity.notFound().build());
    }
}
