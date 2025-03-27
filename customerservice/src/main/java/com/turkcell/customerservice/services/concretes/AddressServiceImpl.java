package com.turkcell.customerservice.services.concretes;

import com.essoft.dto.address.GetAddressResponse;
import com.turkcell.customerservice.entities.Address;
import com.turkcell.customerservice.repositories.AddressRepository;
import com.turkcell.customerservice.services.abstracts.AddressService;
import com.turkcell.customerservice.services.dtos.requests.addressRequests.CreateAddressRequest;
import com.turkcell.customerservice.services.dtos.requests.addressRequests.UpdateAddressRequest;
import com.turkcell.customerservice.services.dtos.responses.addressResponses.*;
import com.turkcell.customerservice.services.mappers.AddressMapper;
import com.turkcell.customerservice.services.rules.AddressBusinessRules;
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
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;
    private AddressBusinessRules addressBusinessRules;
    private AuditAwareImpl auditAware;

    @Override
    public GetListResponse<GetAllAddressResponse> getAll(PageInfo pageInfo) {
      return ListResponse.get(pageInfo, addressRepository, AddressMapper.INSTANCE::getAllAddressResponseFromAddress);
    }

    @Override
    public GetAddressResponse getById(UUID id) {
        addressBusinessRules.checkAddressIdExists(id);
        Address foundAddress = addressRepository.findById(id).get();
        return AddressMapper.INSTANCE.getAddressResponseFromAddress(foundAddress);
    }

    @Override
    public CreatedAddressResponse add(CreateAddressRequest request) {
        Address address = AddressMapper.INSTANCE.addressFromCreateAddressRequest(request);
        Address createdAddress = addressRepository.save(address);
        return AddressMapper.INSTANCE.createdAddressResponseFromAddress(createdAddress);
    }

    @Override
    public UpdatedAddressResponse update(UpdateAddressRequest request, UUID id) {
        addressBusinessRules.checkAddressIdExists(id);
        Address foundAddress = addressRepository.findById(id).get();
        Address address = AddressMapper.INSTANCE.addressFromUpdateAddressRequest(request);
        address.setId(foundAddress.getId());
        Address updatedAddress = addressRepository.save(address);
        return AddressMapper.INSTANCE.updatedAddressResponseFromAddress(updatedAddress);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        addressBusinessRules.checkAddressIdExists(id);
        addressRepository.softDelete(id, LocalDateTime.now(), auditAware.getCurrentAuditor().toString());
    }
}
