package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.services.abstracts.AddressService;
import com.turkcell.customerservice.services.dtos.requests.addressRequests.CreateAddressRequest;
import com.turkcell.customerservice.services.dtos.requests.addressRequests.UpdateAddressRequest;
import com.turkcell.customerservice.services.dtos.responses.addressResponses.*;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/addresses")
@AllArgsConstructor
public class AddressesController {
    private AddressService addressService;

    @PostMapping
    public CreatedAddressResponse add(@Valid @RequestBody CreateAddressRequest createAddressRequest) {
        return addressService.add(createAddressRequest);
    }

    @GetMapping()
    public GetListResponse<GetAllAddressResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return addressService.getAll(new PageInfo(page, size));
    }

    @GetMapping("/{id}")
    public GetAddressResponse getById(@PathVariable UUID id) {
        return addressService.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedAddressResponse update(@Valid @RequestBody UpdateAddressRequest updateAddressRequest, @PathVariable UUID id) {
        return addressService.update(updateAddressRequest, id);
    }

    @DeleteMapping("/{id}")
    public DeletedAddressResponse delete(@PathVariable UUID id) {
        return addressService.delete(id);
    }

    @GetMapping("/customerid/{customerId}")
    public List<GetAddressByCustomerIdResponse> getByCustomerId(@PathVariable UUID customerId) {
        return addressService.getByCustomerId(customerId);
    }

}
