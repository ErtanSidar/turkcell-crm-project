package com.turkcell.customerservice.services.mappers;

import com.turkcell.customerservice.entities.Contact;
import com.turkcell.customerservice.services.dtos.requests.contactRequests.CreateContactRequest;
import com.turkcell.customerservice.services.dtos.requests.contactRequests.UpdateContactRequest;
import com.turkcell.customerservice.services.dtos.responses.contactResponses.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    GetAllContactResponse getAllContactResponseFromContact(Contact contact);

    @Mapping(source = "createContactRequest.customerId", target = "customer.id")
    Contact contactFromCreateContactRequest(CreateContactRequest createContactRequest);

    CreatedContactResponse createdContactResponseFromContact(Contact contact);


    GetContactResponse getContactResponseFromContact(Contact contact);

    Contact contactFromUpdateContactRequest(UpdateContactRequest updateContactRequest);

    UpdatedContactResponse updatedContactResponseFromContact(Contact contact);

}
