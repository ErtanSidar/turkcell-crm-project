package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.services.abstracts.IndividualCustomerService;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.CheckTurkishCitizenRequest;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.IndividualCustomerResponses.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/individualcustomers")
@AllArgsConstructor
public class IndividualCustomerController {
    private IndividualCustomerService individualCustomerService;

    @PostMapping
    public CreatedIndividualCustomerResponse add(@Valid @RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest) throws Exception {
        return individualCustomerService.add(createIndividualCustomerRequest);
    }

    @PostMapping("/checkmernis")
    public boolean checkIfRealPerson(@RequestBody CheckTurkishCitizenRequest checkTurkishCitizenRequest) throws Exception {
        return individualCustomerService.checkIfTurkishCitizen(checkTurkishCitizenRequest);
    }

    @GetMapping
    public List<GetAllIndividualCustomerResponse> findAll() {
        return individualCustomerService.findAll();
    }

    @GetMapping("/nationalityid")
    public boolean isIndividualCustomerExistsByNationalityId(String nationalityId) {
        return individualCustomerService.isIndividualCustomerExistsByNationalityId(nationalityId);
    }

    @GetMapping("/{id}")
    public GetIndividualCustomerResponse findById(@PathVariable UUID id) {
        return individualCustomerService.findById(id);
    }

    @PutMapping("/{id}")
    public UpdatedIndividualCustomerResponse update(
            @Valid @RequestBody UpdateIndividualCustomerRequest updateIndividualCustomerRequest, @PathVariable UUID id) throws Exception {
        return individualCustomerService.update(updateIndividualCustomerRequest, id);
    }

    @DeleteMapping("/{id}")
    public DeletedIndividualCustomerResponse delete(@PathVariable UUID id) {
        return individualCustomerService.delete(id);
    }
}
