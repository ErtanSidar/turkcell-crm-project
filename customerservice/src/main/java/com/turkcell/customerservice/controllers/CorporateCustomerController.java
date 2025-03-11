package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.services.abstracts.CorporateCustomerService;
import com.turkcell.customerservice.services.dtos.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.corporateCustomerResponses.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/corporatecustomers")
@AllArgsConstructor
public class CorporateCustomerController {

    private CorporateCustomerService corporateCustomerService;

    @PostMapping
    public CreatedCorporateCustomerResponse add(@Valid @RequestBody CreateCorporateCustomerRequest request) throws Exception {
        return corporateCustomerService.add(request);
    }

    @GetMapping
    public List<GetAllCorporateCustomerResponse> findAll() {
        return corporateCustomerService.findAll();
    }


    @GetMapping("/{id}")
    public GetCorporateCustomerResponse findById(@PathVariable UUID id) {
        return corporateCustomerService.findById(id);
    }

    @PutMapping("/{id}")
    public UpdatedCorporateCustomerResponse update(
            @Valid @RequestBody UpdateCorporateCustomerRequest request, @PathVariable UUID id) throws Exception{
        return corporateCustomerService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public DeletedCorporateCustomerResponse delete(@PathVariable UUID id) {
        return corporateCustomerService.delete(id);
    }
}
