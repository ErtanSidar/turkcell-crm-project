package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.core.business.Utility;
import com.turkcell.customerservice.services.abstracts.DistrictService;
import com.turkcell.customerservice.services.dtos.requests.districtRequests.CreateDistrictRequest;
import com.turkcell.customerservice.services.dtos.requests.districtRequests.UpdateDistrictRequest;
import com.turkcell.customerservice.services.dtos.responses.districtResponses.*;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
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
    public CreatedDistrictResponse add(@Valid @RequestBody CreateDistrictRequest request) {
        return districtService.add(request);
    }

    @GetMapping
    public GetListResponse<GetAllDistrictResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return districtService.getAll(new PageInfo(page, size));
    }

    @GetMapping("/{id}")
    public GetDistrictResponse getById(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        return districtService.getById(id);
    }

    @PutMapping("/{id}")
    public UpdatedDistrictResponse update(@Valid @RequestBody UpdateDistrictRequest request, @PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        return districtService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        Utility.checkIdIsEmpty(id);
        districtService.delete(id);
    }

    @GetMapping("/cityid/{cityId}")
    public List<GetDistrictByCityIdResponse> getByCityId(@PathVariable UUID cityId) {
        Utility.checkIdIsEmpty(cityId);
        return districtService.getByCityId(cityId);
    }
}
