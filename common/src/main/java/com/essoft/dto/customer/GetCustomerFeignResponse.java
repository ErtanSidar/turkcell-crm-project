package com.essoft.dto.customer;

import com.essoft.dto.address.GetAddressResponse;
import com.essoft.dto.campaign.GetCampaignResponse;
import com.essoft.dto.contact.GetContactResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class GetCustomerFeignResponse {

    private UUID id;

    private String firstName;

    private String lastName;

    private String nationalityId;

    private LocalDate birthDate;

    private String companyName;

    private String taxNumber;

    private String customerNumber;

    private String customerType;

    private List<GetContactResponse> contacts;

    private List<GetAddressResponse> addresses;

    private List<GetCampaignResponse> campaigns;
}
