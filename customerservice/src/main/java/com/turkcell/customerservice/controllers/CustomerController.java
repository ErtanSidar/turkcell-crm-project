package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.core.business.Utility;
import com.turkcell.customerservice.services.abstracts.CustomerService;
import com.turkcell.customerservice.services.dtos.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.customerRequests.CreateCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.customerRequests.UpdateCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.IndividualCustomerResponses.CreatedIndividualCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.corporateCustomerResponses.CreatedCorporateCustomerResponse;
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

    @GetMapping
    public GetListResponse<GetAllCustomerResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return customerService.getAll(new PageInfo(page, size));
    }

    @GetMapping("/{id}")
    public GetCustomerResponse findById(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        return customerService.findById(id);
    }

    @PostMapping("/individual")
    public CreatedIndividualCustomerResponse add(@Valid @RequestBody CreateIndividualCustomerRequest request) throws Exception {
        return customerService.add(request);
    }

    @PostMapping("/corporate")
    public CreatedCorporateCustomerResponse add(@Valid @RequestBody CreateCorporateCustomerRequest request) throws Exception {
        return customerService.add(request);
    }

    @PutMapping("/{id}")
    public UpdatedCustomerResponse update(
            @Valid @RequestBody UpdateCustomerRequest request, @PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        return customerService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        customerService.delete(id);
    }

    @PostMapping("/{customerId}/campaign/{campaignId}")
    public void addCustomerToCampaign(@PathVariable UUID customerId,
                                      @PathVariable UUID campaignId) {
        customerService.addCustomerToCampaign(customerId, campaignId);
    }
}
