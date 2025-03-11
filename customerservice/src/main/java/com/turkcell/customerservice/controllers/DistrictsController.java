package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.services.abstracts.DistrictService;
import com.turkcell.customerservice.services.dtos.requests.districtRequests.CreateDistrictRequest;
import com.turkcell.customerservice.services.dtos.requests.districtRequests.UpdateDistrictRequest;
import com.turkcell.customerservice.services.dtos.responses.districtResponses.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/districts")
@AllArgsConstructor
public class DistrictsController {
    private DistrictService districtService;

    @PostMapping
    public CreatedDistrictResponse add(@Valid @RequestBody CreateDistrictRequest createDistrictRequest) {
        return districtService.add(createDistrictRequest);
    }

    @GetMapping
    public List<GetAllDistrictResponse> getAll() {
        return districtService.getAll();
    }

    @GetMapping("/{id}")
    public GetDistrictResponse getById(@PathVariable UUID id) {
        return districtService.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedDistrictResponse update(@Valid @RequestBody UpdateDistrictRequest updateDistrictRequest, @PathVariable UUID id) {
        return districtService.update(updateDistrictRequest, id);
    }

    @DeleteMapping("/{id}")
    public DeletedDistrictResponse delete(@PathVariable UUID id) {
        return districtService.delete(id);
    }

    @GetMapping("/cityid/{cityId}")
    public List<GetDistrictByCityIdResponse> getByCityId(@PathVariable UUID cityId){
        return districtService.getByCityId(cityId);
    }
}
