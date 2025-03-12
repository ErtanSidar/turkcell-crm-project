package com.turkcell.analyticservice.application.packages.command.delete;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeletedPackageResponse {
    private boolean success;
    private String message;
}