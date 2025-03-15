package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.Address;
import com.turkcell.customerservice.repositories.AddressRepository;
import com.turkcell.customerservice.services.abstracts.AddressService;
import com.turkcell.customerservice.services.dtos.requests.addressRequests.CreateAddressRequest;
import com.turkcell.customerservice.services.dtos.requests.addressRequests.UpdateAddressRequest;
import com.turkcell.customerservice.services.dtos.responses.addressResponses.*;
import com.turkcell.customerservice.services.mappers.AddressMapper;
import com.turkcell.customerservice.services.rules.AddressBusinessRules;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;
    private AddressBusinessRules addressBusinessRules;

    @Override
    public GetListResponse<GetAllAddressResponse> getAll(PageInfo pageInfo) {
      return ListResponse.get(pageInfo, addressRepository, AddressMapper.INSTANCE::getAllAddressResponseFromAddress);
    }

    @Override
    public GetAddressResponse getById(UUID id) {
        addressBusinessRules.addressNotFound(id);

        Address foundAddress = addressRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Address not found"));
        return AddressMapper.INSTANCE.getAddressResponseFromAddress(foundAddress);
    }

    @Override
    public CreatedAddressResponse add(CreateAddressRequest request) {
        Address address =
                AddressMapper.INSTANCE.addressFromCreateAddressRequest(request);
        address.setId(UUID.randomUUID());
        Address createdAddress = addressRepository.save(address);
        return AddressMapper.INSTANCE.createdAddressResponseFromAddress(createdAddress);
    }

    @Override
    public UpdatedAddressResponse update(UpdateAddressRequest request, UUID id) {
        addressBusinessRules.addressNotFound(id);
        addressBusinessRules.addressIsDeleted(id);

        Address foundAddress = addressRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Address not found"));

        Address address =
                AddressMapper.INSTANCE.addressFromUpdateAddressRequest(request);
        address.setId(foundAddress.getId());

        Address updatedAddress = addressRepository.save(address);
        return AddressMapper.INSTANCE.updatedAddressResponseFromAddress(updatedAddress);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        addressBusinessRules.addressNotFound(id);
        addressBusinessRules.addressIsDeleted(id);
        addressRepository.softDelete(id, LocalDateTime.now(), AuditAwareImpl.USER);
    }

    @Override
    public List<GetAddressByCustomerIdResponse> getByCustomerId(UUID customerId) {
        List<Address> addresses = this.addressRepository.findByCustomerId(customerId);
        return addresses
                .stream().map(AddressMapper.INSTANCE::getAddressByCustomerIdResponseFromAddress).toList();
    }
}
