package com.turkcell.customersupportservice.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedTicketResponse {
    private UUID id;                // Ticket ID'si
    private String subject;          // Ticket konusu
    private String description;      // Ticket açıklaması
    private String status;           // Ticket durumu (Örneğin: Açık, Kapanmış vb.)
    private UUID customerId;         // Ticket'ı açan müşterinin ID'si
    private LocalDateTime updatedAt; // Güncelleme zamanı
    private String updatedBy;
}
