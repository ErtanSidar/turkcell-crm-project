package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.core.business.Utility;
import com.turkcell.customerservice.services.abstracts.CityService;
import com.turkcell.customerservice.services.dtos.requests.cityRequests.CreateCityRequest;
import com.turkcell.customerservice.services.dtos.requests.cityRequests.UpdateCityRequest;
import com.turkcell.customerservice.services.dtos.responses.cityResponses.CreatedCityResponse;
import com.turkcell.customerservice.services.dtos.responses.cityResponses.GetAllCityResponse;
import com.turkcell.customerservice.services.dtos.responses.cityResponses.GetCityResponse;
import com.turkcell.customerservice.services.dtos.responses.cityResponses.UpdatedCityResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/cities")
@AllArgsConstructor
public class CitiesController {
    private CityService cityService;

    @PostMapping
    public CreatedCityResponse add(@Valid @RequestBody CreateCityRequest request) {
        return cityService.add(request);
    }

    @GetMapping
    public GetListResponse<GetAllCityResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return cityService.getAll(new PageInfo(page, size));
    }

    @GetMapping("/{id}")
    public GetCityResponse getById(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        return cityService.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedCityResponse update(@Valid @RequestBody UpdateCityRequest request, @PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        return cityService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        cityService.delete(id);
    }
}
