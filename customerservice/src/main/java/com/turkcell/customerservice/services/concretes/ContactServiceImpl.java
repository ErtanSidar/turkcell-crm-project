package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.Contact;
import com.turkcell.customerservice.repositories.ContactRepository;
import com.turkcell.customerservice.services.abstracts.ContactService;
import com.turkcell.customerservice.services.abstracts.IndividualCustomerService;
import com.turkcell.customerservice.services.dtos.requests.contactRequests.CreateContactRequest;
import com.turkcell.customerservice.services.dtos.requests.contactRequests.UpdateContactRequest;
import com.turkcell.customerservice.services.dtos.responses.IndividualCustomerResponses.GetIndividualCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.contactResponses.*;
import com.turkcell.customerservice.services.mappers.ContactMapper;
import com.turkcell.customerservice.services.rules.ContactBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {
    private ContactRepository contactRepository;
    private IndividualCustomerService individualCustomerService;
    private ContactBusinessRules contactBusinessRules;

    @Override
    public CreatedContactResponse add(CreateContactRequest createContactRequest) {
        Contact contact =
                ContactMapper.INSTANCE.contactFromCreateContactRequest(createContactRequest);
        contact.setId(UUID.randomUUID());
        contact.setCreatedDate(LocalDateTime.now());
        Contact savedContact = this.contactRepository.save(contact);

        GetIndividualCustomerResponse individualCustomer =
                individualCustomerService.findById(createContactRequest.getCustomerId());

        return ContactMapper.INSTANCE.createdContactResponseFromContact(savedContact);
    }

    @Override
    public List<GetAllContactResponse> findAll() {
        List<Contact> contactMediumsList = this.contactRepository.findAll();
        List<GetAllContactResponse> getAllContactResponses = contactMediumsList.stream()
                .filter(contactMedium -> contactMedium.getDeletedDate() == null)
                .map(contactMedium ->
                        ContactMapper.INSTANCE
                                .getAllContactResponseFromContact(contactMedium)).collect(Collectors.toList());
        return getAllContactResponses;
    }

    @Override
    public GetContactResponse findById(UUID id) {
        contactBusinessRules.contactMediumNotFound(id);
        contactBusinessRules.contactMediumIsDeleted(id);

        Contact contact = this.contactRepository.findById(id).get();
        return ContactMapper.INSTANCE.getContactResponseFromContact(contact);
    }

    @Override
    public GetContactByCustomerIdResponse getByCustomerId(UUID customerId) {
        Contact contact = this.contactRepository.findByCustomerId(customerId);
        return ContactMapper.INSTANCE.getContactByCustomerIdResponseFromContact(contact);
    }

    @Override
    public UpdatedContactResponse update(UpdateContactRequest updateContactRequest, UUID id) {
        contactBusinessRules.contactMediumNotFound(id);
        contactBusinessRules.contactMediumIsDeleted(id);

        return null;
    }

    @Override
    public DeletedContactResponse delete(UUID id) {
        contactBusinessRules.contactMediumNotFound(id);
        contactBusinessRules.contactMediumIsDeleted(id);

        Contact contact = this.contactRepository.findById(id).get();
        contact.setDeletedDate(LocalDateTime.now());
        Contact savedContact = this.contactRepository.save(contact);
        return ContactMapper.INSTANCE.deletedContactResponseFromContact(savedContact);
    }
}
