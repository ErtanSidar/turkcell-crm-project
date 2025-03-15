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
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {
    private ContactRepository contactRepository;
    private IndividualCustomerService individualCustomerService;
    private ContactBusinessRules contactBusinessRules;

    @Override
    public CreatedContactResponse add(CreateContactRequest request) {
        Contact contact =
                ContactMapper.INSTANCE.contactFromCreateContactRequest(request);
        contact.setId(UUID.randomUUID());
        Contact savedContact = this.contactRepository.save(contact);

        GetIndividualCustomerResponse individualCustomer =
                individualCustomerService.findById(request.getCustomerId());

        return ContactMapper.INSTANCE.createdContactResponseFromContact(savedContact);
    }

    @Override
    public GetListResponse<GetAllContactResponse> getAll(PageInfo pageInfo) {
        return ListResponse.get(pageInfo, contactRepository, ContactMapper.INSTANCE::getAllContactResponseFromContact);
    }

    @Override
    public GetContactResponse findById(UUID id) {
        contactBusinessRules.contactMediumNotFound(id);
        contactBusinessRules.contactMediumIsDeleted(id);

        Contact contact = this.contactRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Contact not found"));
        return ContactMapper.INSTANCE.getContactResponseFromContact(contact);
    }

    @Override
    public GetContactByCustomerIdResponse getByCustomerId(UUID customerId) {
        Contact contact = this.contactRepository.findByCustomerId(customerId);
        return ContactMapper.INSTANCE.getContactByCustomerIdResponseFromContact(contact);
    }

    @Override
    public UpdatedContactResponse update(UpdateContactRequest request, UUID id) {
        contactBusinessRules.contactMediumNotFound(id);
        contactBusinessRules.contactMediumIsDeleted(id);

        return null;
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        contactBusinessRules.contactMediumNotFound(id);
        contactBusinessRules.contactMediumIsDeleted(id);
        contactRepository.softDelete(id, LocalDateTime.now(), AuditAwareImpl.USER);
    }
}
