package com.maripavlova.clients.purchase;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "purchase",
        url = "${clients.purchase.url}"
)
public interface PurchaseClient {
    @GetMapping(path = "api/v1/purchase-check/{customerId}")
    PurchaseCheckResponse isValid(@PathVariable("customerId") Integer customerId);
}
