package com.essoft.event.ticket;

import com.essoft.entities.EmailTemplateName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketCreatedEvent {
    private UUID customerId;
    private String subject;
    private String description;
    private String status;
    private final EmailTemplateName emailTemplateName = EmailTemplateName.CUSTOMER_SUPPORT;

}
