package com.turkcell.billingpaymentservice.cqrs.controllers;

import an.awesome.pipelinr.Pipeline;
import com.turkcell.billingpaymentservice.cqrs.application.billing.command.create.CreateBillingCommand;
import com.turkcell.billingpaymentservice.cqrs.application.billing.command.create.CreatedBillingResponse;
import com.turkcell.billingpaymentservice.cqrs.application.billing.command.delete.DeleteBillingCommand;
import com.turkcell.billingpaymentservice.cqrs.application.billing.command.delete.DeletedBillingResponse;
import com.turkcell.billingpaymentservice.cqrs.application.billing.command.update.UpdateBillingCommand;
import com.turkcell.billingpaymentservice.cqrs.application.billing.command.update.UpdatedBillingResponse;
import com.turkcell.billingpaymentservice.cqrs.application.billing.query.getbyid.GetBillingByIdQuery;
import com.turkcell.billingpaymentservice.cqrs.application.billing.query.getlist.GetListBillingQuery;
import com.turkcell.billingpaymentservice.cqrs.application.billing.query.getlist.GetListBillingResponse;
import io.github.ertansidar.web.BaseController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/billings")
public class BillingsController extends BaseController {

    public BillingsController(Pipeline pipeline) {
        super(pipeline);
    }

    @GetMapping
    public Object getById(@RequestParam UUID id) {
        GetBillingByIdQuery getBillingByIdQuery = new GetBillingByIdQuery();
        getBillingByIdQuery.setId(id);
        return getBillingByIdQuery.execute(pipeline);
    }

    @GetMapping("/all")
    public List<GetListBillingResponse> getAll() {
        GetListBillingQuery getListBillingQuery = new GetListBillingQuery();
        return getListBillingQuery.execute(pipeline);
    }

    @PostMapping
    public CreatedBillingResponse create(@RequestBody CreateBillingCommand createBillingCommand) {
        return createBillingCommand.execute(pipeline);
    }

    @PutMapping
    public UpdatedBillingResponse update(@RequestBody UpdateBillingCommand updateBillingCommand) {
        return updateBillingCommand.execute(pipeline);
    }

    @DeleteMapping
    public DeletedBillingResponse delete(@RequestBody DeleteBillingCommand deleteBillingCommand) {
        return deleteBillingCommand.execute(pipeline);
    }
}
