package com.maripavlova.clients.purchase;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="purchase")
public interface PurchaseClient {
    @GetMapping(path = "api/v1/purchase-check/{customerId}")
    PurchaseCheckResponse isValid(@PathVariable("customerId") Integer customerId);
}
