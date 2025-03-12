package com.turkcell.analyticservice.application.product.command.delete;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeletedProductResponse {
    private UUID id;
    private String message;
}