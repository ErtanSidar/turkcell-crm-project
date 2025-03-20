package com.turkcell.analyticservice.application.usage.query.list;

import com.turkcell.analyticservice.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class GetListUsageItemDto {
     private UUID id;
    private UUID customerId;
    private Product product;
    private int internetUsed;
    private int callMinutesUsed;
    private int smsUsed;
    private int tvHoursWatched;
}
