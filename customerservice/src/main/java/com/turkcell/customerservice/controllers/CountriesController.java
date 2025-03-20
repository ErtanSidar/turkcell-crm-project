package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.core.business.Utility;
import com.turkcell.customerservice.services.abstracts.CountryService;
import com.turkcell.customerservice.services.dtos.requests.countryRequests.CreateCountryRequest;
import com.turkcell.customerservice.services.dtos.requests.countryRequests.UpdateCountryRequest;
import com.turkcell.customerservice.services.dtos.responses.countryResponses.CreatedCountryResponse;
import com.turkcell.customerservice.services.dtos.responses.countryResponses.GetAllCountryResponse;
import com.turkcell.customerservice.services.dtos.responses.countryResponses.GetCountryResponse;
import com.turkcell.customerservice.services.dtos.responses.countryResponses.UpdatedCountryResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/countries")
@AllArgsConstructor
public class CountriesController {
    private CountryService countryService;

    @PostMapping
    public CreatedCountryResponse add(@Valid @RequestBody CreateCountryRequest request) {
        return countryService.add(request);
    }

    @GetMapping
    public GetListResponse<GetAllCountryResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return countryService.getAll(new PageInfo(page, size));
    }

    @GetMapping("/{id}")
    public GetCountryResponse getById(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        return countryService.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedCountryResponse update(@Valid @RequestBody UpdateCountryRequest request, @PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        return countryService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        countryService.delete(id);
    }


}
