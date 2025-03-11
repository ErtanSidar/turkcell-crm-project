package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.services.abstracts.CityService;
import com.turkcell.customerservice.services.dtos.requests.cityRequests.CreateCityRequest;
import com.turkcell.customerservice.services.dtos.requests.cityRequests.UpdateCityRequest;
import com.turkcell.customerservice.services.dtos.responses.cityResponses.*;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/cities")
@AllArgsConstructor
public class CitiesController {
    private CityService cityService;

    @PostMapping
    public CreatedCityResponse add(@Valid @RequestBody CreateCityRequest createCityRequest) {
        return cityService.add(createCityRequest);
    }

    @GetMapping
    public GetListResponse<GetAllCityResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return cityService.getAll(new PageInfo(page, size));
    }

    @GetMapping("/{id}")
    public GetCityResponse getById(@PathVariable UUID id) {
        return cityService.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedCityResponse update(@Valid @RequestBody UpdateCityRequest updateCityRequest, @PathVariable UUID id) {
        return cityService.update(updateCityRequest, id);
    }

    @DeleteMapping("/{id}")
    public DeletedCityResponse delete(@PathVariable UUID id) {
        return cityService.delete(id);
    }

    @GetMapping("/countryid/{countryId}")
    public List<GetCityByCountryIdResponse> getByCountryId(@PathVariable UUID countryId){
        return cityService.getByCountryId(countryId);
    }
}
