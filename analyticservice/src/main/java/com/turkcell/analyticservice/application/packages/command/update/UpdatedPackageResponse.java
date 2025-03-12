package com.turkcell.analyticservice.application.packages.command.update;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedPackageResponse {
    private boolean success;
    private String message;
}