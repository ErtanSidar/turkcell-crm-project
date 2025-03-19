package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.core.business.Utility;
import com.turkcell.customerservice.services.abstracts.CustomerService;
import com.turkcell.customerservice.services.dtos.requests.customerRequests.CreateCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.customerRequests.UpdateCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.CreatedCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.GetAllCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.GetCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.UpdatedCustomerResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public CreatedCustomerResponse add(@Valid @RequestBody CreateCustomerRequest request) {
        return customerService.add(request);
    }

    @GetMapping
    public GetListResponse<GetAllCustomerResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return customerService.getAll(new PageInfo(page, size));
    }

    @GetMapping("/{id}")
    public GetCustomerResponse findById(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        return customerService.findById(id);
    }

    @PutMapping("/{id}")
    public UpdatedCustomerResponse update(
            @Valid @RequestBody UpdateCustomerRequest request, @PathVariable UUID id) {
        return customerService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        customerService.delete(id);
    }
}
