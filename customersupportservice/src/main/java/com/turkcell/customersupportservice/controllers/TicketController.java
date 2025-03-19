package com.turkcell.customersupportservice.controllers;

import com.turkcell.customersupportservice.coreBusiness.Utility;
import com.turkcell.customersupportservice.services.abstracts.TicketService;
import com.turkcell.customersupportservice.services.dtos.requests.CreateTicketRequest;
import com.turkcell.customersupportservice.services.dtos.requests.UpdateTicketRequest;
import com.turkcell.customersupportservice.services.dtos.responses.*;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;


    @PostMapping
    public CreatedTicketResponse add(@Valid @RequestBody CreateTicketRequest request){
        return ticketService.add(request);
    }

    @PutMapping("/{id}")
    public UpdatedTicketResponse update(@Valid @RequestBody UpdateTicketRequest request, @PathVariable UUID id){
        Utility.checkIdIsEmpty(id);
        return ticketService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public DeletedTicketResponse delete(@PathVariable UUID id){
        Utility.checkIdIsEmpty(id);
        return ticketService.delete(id);
    }


    @GetMapping
    public GetListResponse<GetAllTicketResponse> getAll(@RequestParam int page, @RequestParam int size){
        return ticketService.getAll(new PageInfo(page,size));
    }
    @GetMapping("{id}")
    public GetTicketResponse getById(@PathVariable UUID id){
        Utility.checkIdIsEmpty(id);
        return ticketService.getById(id);
    }

}
