package com.patika.bloghubservice.client.payment.dto.response;

import com.patika.bloghubservice.client.payment.dto.response.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentResponse {
    private BigDecimal amount;
    private LocalDateTime createDateTime;
    private PaymentStatus paymentStatus;
    private Long userId;
}
