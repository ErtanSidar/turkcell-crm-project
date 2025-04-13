package com.essoft.event.packageEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackageCreatedEvent {


    private String packageName;

    private String packageType;


    private int quota;

    private double price;

    private int validityPeriod;

}
