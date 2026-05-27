package com.dio.storefront.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "warehouse", url = "${warehouse.service.url:http://localhost:8081}")
public interface WarehouseClient {

    @GetMapping("/stock/{id}")
    Integer getStock(@PathVariable("id") String id);
}
