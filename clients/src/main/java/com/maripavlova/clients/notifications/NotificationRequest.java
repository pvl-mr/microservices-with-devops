package com.maripavlova.clients.notifications;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerName,
        String message
) {
}
