package com.maripavlova.purchase;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PurchaseCheckService {

    private final PurchaseCheckRepository purchaseCheckRepository;

    public PurchaseCheckService(PurchaseCheckRepository purchaseCheckRepository) {
        this.purchaseCheckRepository = purchaseCheckRepository;
    }
    public boolean isValidPurchase(Integer customerId) {
        purchaseCheckRepository.save(
                PurchaseCheck.builder()
                        .customerId(customerId)
                        .isValid(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
