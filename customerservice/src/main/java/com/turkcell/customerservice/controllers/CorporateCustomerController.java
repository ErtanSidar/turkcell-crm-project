package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.core.business.Utility;
import com.turkcell.customerservice.services.abstracts.CorporateCustomerService;
import com.turkcell.customerservice.services.dtos.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.corporateCustomerResponses.CreatedCorporateCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.corporateCustomerResponses.GetAllCorporateCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.corporateCustomerResponses.GetCorporateCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.corporateCustomerResponses.UpdatedCorporateCustomerResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public GetListResponse<GetAllCorporateCustomerResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return corporateCustomerService.getAll(new PageInfo(page, size));
    }


    @GetMapping("/{id}")
    public GetCorporateCustomerResponse findById(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        return corporateCustomerService.findById(id);
    }

    @PutMapping("/{id}")
    public UpdatedCorporateCustomerResponse update(
            @Valid @RequestBody UpdateCorporateCustomerRequest request, @PathVariable UUID id) throws Exception {
        Utility.checkIdIsEmpty(id);
        return corporateCustomerService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        corporateCustomerService.delete(id);
    }
}
