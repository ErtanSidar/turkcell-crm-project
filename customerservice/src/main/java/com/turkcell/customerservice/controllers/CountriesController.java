package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.services.abstracts.CountryService;
import com.turkcell.customerservice.services.dtos.requests.countryRequests.CreateCountryRequest;
import com.turkcell.customerservice.services.dtos.requests.countryRequests.UpdateCountryRequest;
import com.turkcell.customerservice.services.dtos.responses.countryResponses.*;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        return countryService.getAll(new PageInfo(page,size));
    }

    @GetMapping("/{id}")
    public GetCountryResponse getById(@PathVariable UUID id) {
        checkCountryIdExists(id);
        return countryService.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedCountryResponse update(@Valid @RequestBody UpdateCountryRequest request, @PathVariable UUID id) {
        checkCountryIdExists(id);
        return countryService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public DeletedCountryResponse delete(@PathVariable UUID id) {
        checkCountryIdExists(id);
        return countryService.delete(id);
    }

    private void checkCountryIdExists(UUID id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new BusinessException("Id cannot be null");
        }
    }
}
