package com.turkcell.billingpaymentservice.cqrs.controllers;

import an.awesome.pipelinr.Pipeline;
import com.turkcell.billingpaymentservice.cqrs.application.payment.command.create.CreatePaymentCommand;
import com.turkcell.billingpaymentservice.cqrs.application.payment.command.create.CreatedPaymentResponse;
import com.turkcell.billingpaymentservice.cqrs.application.payment.command.delete.DeletePaymentCommand;
import com.turkcell.billingpaymentservice.cqrs.application.payment.command.delete.DeletedPaymentResponse;
import com.turkcell.billingpaymentservice.cqrs.application.payment.command.update.UpdatePaymentCommand;
import com.turkcell.billingpaymentservice.cqrs.application.payment.command.update.UpdatedPaymentResponse;
import com.turkcell.billingpaymentservice.cqrs.application.payment.query.getbyid.GetPaymentByIdQuery;
import com.turkcell.billingpaymentservice.cqrs.application.payment.query.getlist.GetListPaymentQuery;
import com.turkcell.billingpaymentservice.cqrs.application.payment.query.getlist.GetListPaymentResponse;
import io.github.ertansidar.web.BaseController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentsController extends BaseController {

    public PaymentsController(Pipeline pipeline) {
        super(pipeline);
    }

    @GetMapping
    public Object getById(@RequestParam UUID id) {
        GetPaymentByIdQuery getPaymentByIdQuery = new GetPaymentByIdQuery();
        getPaymentByIdQuery.setId(id);
        return getPaymentByIdQuery.execute(pipeline);
    }

    @GetMapping("/all")
    public List<GetListPaymentResponse> getAll() {
        GetListPaymentQuery getListPaymentQuery = new GetListPaymentQuery();
        return getListPaymentQuery.execute(pipeline);
    }

    @PostMapping
    public CreatedPaymentResponse create(@RequestBody CreatePaymentCommand createPaymentCommand) {
        return createPaymentCommand.execute(pipeline);
    }

    @PutMapping
    public UpdatedPaymentResponse update(@RequestBody UpdatePaymentCommand updatePaymentCommand) {
        return updatePaymentCommand.execute(pipeline);
    }

    @DeleteMapping
    public DeletedPaymentResponse delete(@RequestBody DeletePaymentCommand deletePaymentCommand) {
        return deletePaymentCommand.execute(pipeline);
    }
}
