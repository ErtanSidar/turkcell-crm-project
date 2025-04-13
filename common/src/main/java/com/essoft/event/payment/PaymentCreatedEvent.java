package com.essoft.event.payment;

import com.essoft.entities.EmailTemplateName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentCreatedEvent {

    private String customerName;

    private String customerEmail;

    private Double amount;

    private LocalDateTime paymentDate;

    private String paymentMethod;

    private String status;

    private final EmailTemplateName emailTemplateName = EmailTemplateName.PAYMENT_NOTIFICATION;
}
