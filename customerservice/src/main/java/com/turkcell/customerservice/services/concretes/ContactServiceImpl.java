package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.Contact;
import com.turkcell.customerservice.repositories.ContactRepository;
import com.turkcell.customerservice.services.abstracts.ContactService;
import com.turkcell.customerservice.services.dtos.requests.contactRequests.CreateContactRequest;
import com.turkcell.customerservice.services.dtos.requests.contactRequests.UpdateContactRequest;
import com.turkcell.customerservice.services.dtos.responses.contactResponses.CreatedContactResponse;
import com.turkcell.customerservice.services.dtos.responses.contactResponses.GetAllContactResponse;
import com.turkcell.customerservice.services.dtos.responses.contactResponses.GetContactResponse;
import com.turkcell.customerservice.services.dtos.responses.contactResponses.UpdatedContactResponse;
import com.turkcell.customerservice.services.mappers.ContactMapper;
import com.turkcell.customerservice.services.rules.ContactBusinessRules;
import io.github.ertansidar.audit.AuditAwareImpl;
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
    private ContactBusinessRules contactBusinessRules;
    private AuditAwareImpl auditAware;

    @Override
    public GetListResponse<GetAllContactResponse> getAll(PageInfo pageInfo) {
        return ListResponse.get(pageInfo, contactRepository, ContactMapper.INSTANCE::getAllContactResponseFromContact);
    }

    @Override
    public GetContactResponse findById(UUID id) {
        contactBusinessRules.checkContactIdExists(id);
        Contact contact = this.contactRepository.findById(id).get();
        return ContactMapper.INSTANCE.getContactResponseFromContact(contact);
    }

    @Override
    public CreatedContactResponse add(CreateContactRequest request) {
        Contact contact = ContactMapper.INSTANCE.contactFromCreateContactRequest(request);
        Contact savedContact = this.contactRepository.save(contact);
        return ContactMapper.INSTANCE.createdContactResponseFromContact(savedContact);
    }

    @Override
    public UpdatedContactResponse update(UpdateContactRequest request, UUID id) {
        contactBusinessRules.checkContactIdExists(id);
        Contact foundContact = contactRepository.findById(id).get();
        Contact contact = ContactMapper.INSTANCE.contactFromUpdateContactRequest(request);
        contact.setId(foundContact.getId());
        Contact updatedContact = contactRepository.save(contact);
        return ContactMapper.INSTANCE.updatedContactResponseFromContact(updatedContact);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        contactBusinessRules.checkContactIdExists(id);
        contactRepository.softDelete(id, LocalDateTime.now(), auditAware.getCurrentAuditor().toString());
    }
}
