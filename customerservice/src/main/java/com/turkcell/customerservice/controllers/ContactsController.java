package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.services.abstracts.ContactService;
import com.turkcell.customerservice.services.dtos.requests.contactRequests.CreateContactRequest;
import com.turkcell.customerservice.services.dtos.requests.contactRequests.UpdateContactRequest;
import com.turkcell.customerservice.services.dtos.responses.contactResponses.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/contact-medium")
@AllArgsConstructor
public class ContactsController {
    private ContactService contactService;

    @PostMapping
    public CreatedContactResponse add(@Valid @RequestBody CreateContactRequest createContactRequest) {
        return contactService.add(createContactRequest);
    }

    @GetMapping
    public List<GetAllContactResponse> findAll() {
        return contactService.findAll();
    }

    @GetMapping("/{id}")
    public GetContactResponse findById(@PathVariable UUID id) {
        return contactService.findById(id);
    }

    @GetMapping("/customerid/{customerId}")
    public GetContactByCustomerIdResponse getByCustomerId(@PathVariable UUID customerId){
        return contactService.getByCustomerId(customerId);
    }

    @PutMapping("/{id}")
    public UpdatedContactResponse update(@Valid
            @RequestBody UpdateContactRequest updateContactMediumRequest, @PathVariable UUID id) {
        return contactService.update(updateContactMediumRequest, id);
    }

    @DeleteMapping("/{id}")
    public DeletedContactResponse delete(@PathVariable UUID id) {
        return contactService.delete(id);
    }
}
