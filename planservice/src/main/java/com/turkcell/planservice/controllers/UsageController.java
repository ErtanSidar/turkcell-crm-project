package com.turkcell.planservice.controllers;

import com.turkcell.planservice.dtos.usagedtos.responses.UsageResponse;
import com.turkcell.planservice.entities.Usage;
import com.turkcell.planservice.services.abstracts.UsageService;
import com.turkcell.planservice.util.GenericResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usages")
public class UsageController {

    private final UsageService usageService;

    public UsageController(UsageService usageService) {
        this.usageService = usageService;
    }

    @GetMapping
    public GetListResponse<UsageResponse> getAllUsages(@RequestParam int page,@RequestParam int size) {
        return usageService.getAllUsages(new PageInfo(page,size));
    }

    @GetMapping("/getOneUsage")
    public Usage getOneUsage(@RequestParam UUID id) {
        return usageService.getOneUsage(id);
    }


    @DeleteMapping
    public GenericResponse<String> deleteUsage(@RequestParam UUID id) {
        usageService.delete(id);
        return GenericResponse.success("generic.usage.deleted");
    }
}
