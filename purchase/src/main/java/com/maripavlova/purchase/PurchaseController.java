package com.maripavlova.purchase;

import com.maripavlova.clients.purchase.PurchaseCheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/purchase-check")
@Slf4j
public class PurchaseController {

    private final PurchaseCheckService purchaseCheckService;

    public PurchaseController(PurchaseCheckService purchaseCheckService) {
        this.purchaseCheckService = purchaseCheckService;
    }
    @GetMapping(path = "{customerId}")
    public PurchaseCheckResponse isValid(@PathVariable("customerId") Integer customerId) {
        boolean isValidPurchase = purchaseCheckService.isValidPurchase(customerId);
        log.info("purchase check for customer {}", customerId);
        return new PurchaseCheckResponse(isValidPurchase);

    }
}
