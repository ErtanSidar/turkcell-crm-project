package com.essoft.dto.contact;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
public class GetContactResponse {
    private UUID id;
    private String email;
    private String homePhone;
    private String mobilePhone;
    private String fax;
    private UUID customerId;
}
