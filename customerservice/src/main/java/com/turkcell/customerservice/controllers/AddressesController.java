package com.turkcell.customerservice.controllers;

import com.essoft.dto.address.GetAddressResponse;
import com.turkcell.customerservice.core.business.Utility;
import com.turkcell.customerservice.services.abstracts.AddressService;
import com.turkcell.customerservice.services.dtos.requests.addressRequests.CreateAddressRequest;
import com.turkcell.customerservice.services.dtos.requests.addressRequests.UpdateAddressRequest;
import com.turkcell.customerservice.services.dtos.responses.addressResponses.CreatedAddressResponse;
import com.turkcell.customerservice.services.dtos.responses.addressResponses.GetAllAddressResponse;
import com.turkcell.customerservice.services.dtos.responses.addressResponses.UpdatedAddressResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/addresses")
@AllArgsConstructor
public class AddressesController {
    private AddressService addressService;

    @PostMapping
    public CreatedAddressResponse add(@Valid @RequestBody CreateAddressRequest request) {
        return addressService.add(request);
    }

    @GetMapping()
    public GetListResponse<GetAllAddressResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return addressService.getAll(new PageInfo(page, size));
    }

    @GetMapping("/{id}")
    public GetAddressResponse getById(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        return addressService.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedAddressResponse update(@Valid @RequestBody UpdateAddressRequest request, @PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        return addressService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        addressService.delete(id);
    }

}
