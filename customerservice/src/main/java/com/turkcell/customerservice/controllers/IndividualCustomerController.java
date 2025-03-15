package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.core.business.Utility;
import com.turkcell.customerservice.services.abstracts.IndividualCustomerService;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.CheckTurkishCitizenRequest;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.IndividualCustomerResponses.CreatedIndividualCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.IndividualCustomerResponses.GetAllIndividualCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.IndividualCustomerResponses.GetIndividualCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.IndividualCustomerResponses.UpdatedIndividualCustomerResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/individualcustomers")
@AllArgsConstructor
public class IndividualCustomerController {
    private IndividualCustomerService individualCustomerService;

    @PostMapping
    public CreatedIndividualCustomerResponse add(@Valid @RequestBody CreateIndividualCustomerRequest request) throws Exception {
        return individualCustomerService.add(request);
    }

    @PostMapping("/checkmernis")
    public boolean checkIfRealPerson(@RequestBody CheckTurkishCitizenRequest request) throws Exception {
        return individualCustomerService.checkIfTurkishCitizen(request);
    }

    @GetMapping
    public GetListResponse<GetAllIndividualCustomerResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return individualCustomerService.getAll(new PageInfo(page, size));
    }

    @GetMapping("/nationalityid")
    public boolean isIndividualCustomerExistsByNationalityId(String nationalityId) {
        return individualCustomerService.isIndividualCustomerExistsByNationalityId(nationalityId);
    }

    @GetMapping("/{id}")
    public GetIndividualCustomerResponse findById(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        return individualCustomerService.findById(id);
    }

    @PutMapping("/{id}")
    public UpdatedIndividualCustomerResponse update(
            @Valid @RequestBody UpdateIndividualCustomerRequest request, @PathVariable UUID id) throws Exception {
        return individualCustomerService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        individualCustomerService.delete(id);
    }
}
