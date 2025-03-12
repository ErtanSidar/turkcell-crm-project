package com.turkcell.analyticservice.application.packages.query;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetListPackageItemDto {
    private UUID id;
    private String packageName;
    private String packageType;
}