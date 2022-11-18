package com.maripavlova.customer;

import com.maripavlova.clients.notifications.NotificationClient;
import com.maripavlova.clients.notifications.NotificationRequest;
import com.maripavlova.clients.purchase.PurchaseCheckResponse;
import com.maripavlova.clients.purchase.PurchaseClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final PurchaseClient purchaseClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);
//        PurchaseCheckResponse purchaseCheckResponse = restTemplate.getForObject(
//                "http://purchase/api/v1/purchase-check/{customerId}",
//                PurchaseCheckResponse.class,
//                customer.getId()
//        );

        PurchaseCheckResponse purchaseCheckResponse = purchaseClient.isValid(customer.getId());

        if (purchaseCheckResponse.isValid()) {
            throw new IllegalStateException("not valid");
        }


        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi, {}",
                                customer.getFirstName())
                )
        );

    }
}
