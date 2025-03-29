package com.turkcell.customerservice.controllers;

import com.essoft.dto.contact.GetContactResponse;
import com.turkcell.customerservice.core.business.Utility;
import com.turkcell.customerservice.services.abstracts.ContactService;
import com.turkcell.customerservice.services.dtos.requests.contactRequests.CreateContactRequest;
import com.turkcell.customerservice.services.dtos.requests.contactRequests.UpdateContactRequest;
import com.turkcell.customerservice.services.dtos.responses.contactResponses.CreatedContactResponse;
import com.turkcell.customerservice.services.dtos.responses.contactResponses.GetAllContactResponse;
import com.turkcell.customerservice.services.dtos.responses.contactResponses.UpdatedContactResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/contacts")
@AllArgsConstructor
public class ContactsController {
    private ContactService contactService;

    @PostMapping
    public CreatedContactResponse add(@Valid @RequestBody CreateContactRequest request) {
        return contactService.add(request);
    }

    @GetMapping
    public GetListResponse<GetAllContactResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return contactService.getAll(new PageInfo(page, size));
    }

    @GetMapping("/{id}")
    public GetContactResponse findById(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        return contactService.findById(id);
    }

    @PutMapping("/{id}")
    public UpdatedContactResponse update(@Valid @RequestBody UpdateContactRequest request, @PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        return contactService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        contactService.delete(id);
    }
}
