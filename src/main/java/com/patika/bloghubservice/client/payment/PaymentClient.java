package com.patika.bloghubservice.client.payment;


import com.patika.bloghubservice.client.payment.dto.request.PaymentRequest;
import com.patika.bloghubservice.client.payment.dto.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "payment-service", url = "http://localhost:8082/api/v1/payments")
public interface PaymentClient {
    @PostMapping
    PaymentResponse createPayment(@RequestBody PaymentRequest request);


}
