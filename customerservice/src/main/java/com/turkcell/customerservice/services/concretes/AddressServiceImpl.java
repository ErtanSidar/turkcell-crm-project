package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.Address;
import com.turkcell.customerservice.repositories.AddressRepository;
import com.turkcell.customerservice.services.abstracts.AddressService;
import com.turkcell.customerservice.services.dtos.requests.addressRequests.CreateAddressRequest;
import com.turkcell.customerservice.services.dtos.requests.addressRequests.UpdateAddressRequest;
import com.turkcell.customerservice.services.dtos.responses.addressResponses.*;
import com.turkcell.customerservice.services.mappers.AddressMapper;
import com.turkcell.customerservice.services.rules.AddressBusinessRules;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        GetListResponse<GetAllAddressResponse> response = new GetListResponse<>();
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        Page<Address> addresses = addressRepository.findAllIfDeletedDateIsNull(pageable);
        response.setItems(addresses.stream()
                .map(AddressMapper.INSTANCE::getAllAddressResponseFromAddress).collect(Collectors.toList()));
        response.setTotalElements(addresses.getTotalElements());
        response.setTotalPage(addresses.getTotalPages());
        response.setSize(addresses.getSize());
        response.setHasNext(addresses.hasNext());
        response.setHasPrevious(addresses.hasPrevious());
        return response;
    }

    @Override
    public GetAddressResponse getById(UUID id) {
        addressBusinessRules.addressNotFound(id);
        addressBusinessRules.addressIsDeleted(id);

        Address foundAddress = addressRepository.findById(id).get();
        GetAddressResponse getAddressResponse = AddressMapper.INSTANCE.getAddressResponseFromAddress(foundAddress);
        return getAddressResponse;
    }

    @Override
    public CreatedAddressResponse add(CreateAddressRequest createAddressRequest) {
        Address address =
                AddressMapper.INSTANCE.addressFromCreateAddressRequest(createAddressRequest);
        address.setCreatedDate(LocalDateTime.now());
        address.setId(UUID.randomUUID());
        Address createdAddress = addressRepository.save(address);
        CreatedAddressResponse createdAddressResponse =
                AddressMapper.INSTANCE.createdAddressResponseFromAddress(createdAddress);
        return createdAddressResponse;
    }

    @Override
    public UpdatedAddressResponse update(UpdateAddressRequest updateAddressRequest, UUID id) {
        addressBusinessRules.addressNotFound(id);
        addressBusinessRules.addressIsDeleted(id);

        Address foundAddress = addressRepository.findById(id).get();

        Address address =
                AddressMapper.INSTANCE.addressFromUpdateAddressRequest(updateAddressRequest);
        address.setId(foundAddress.getId());
        address.setCreatedDate(foundAddress.getCreatedDate());
        address.setUpdatedDate(LocalDateTime.now());

        Address updatedAddress = addressRepository.save(address);
        UpdatedAddressResponse updatedAddressResponse =
                AddressMapper.INSTANCE.updatedAddressResponseFromAddress(updatedAddress);
        return updatedAddressResponse;
    }

    @Override
    public DeletedAddressResponse delete(UUID id) {
        addressBusinessRules.addressNotFound(id);
        addressBusinessRules.addressIsDeleted(id);

        Address foundAddress = addressRepository.findById(id).get();
        foundAddress.setDeletedDate(LocalDateTime.now());
        Address deletedAddress = addressRepository.save(foundAddress);

        DeletedAddressResponse deletedAddressResponse =
                AddressMapper.INSTANCE.deletedAddressResponseFromAddress(deletedAddress);
        return deletedAddressResponse;
    }

    @Override
    public List<GetAddressByCustomerIdResponse> getByCustomerId(UUID customerId) {
        List<Address> addresses = this.addressRepository.findByCustomerId(customerId);
        List<GetAddressByCustomerIdResponse> getAddressByCustomerIdResponse = addresses
                .stream().map(AddressMapper.INSTANCE::getAddressByCustomerIdResponseFromAddress).collect(Collectors.toList());
        return getAddressByCustomerIdResponse;
    }
}
