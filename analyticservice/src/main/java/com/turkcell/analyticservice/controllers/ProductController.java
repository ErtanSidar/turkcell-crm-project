package com.turkcell.analyticservice.controllers;



import an.awesome.pipelinr.Pipeline;
import com.turkcell.analyticservice.application.plan.query.list.GetListPlanItemDto;
import com.turkcell.analyticservice.application.plan.query.list.GetListPlanQuery;
import com.turkcell.analyticservice.application.product.command.create.CreateProductCommand;
import com.turkcell.analyticservice.application.product.command.create.CreatedProductResponse;
import com.turkcell.analyticservice.application.product.command.delete.DeleteProductCommand;
import com.turkcell.analyticservice.application.product.command.delete.DeletedProductResponse;
import com.turkcell.analyticservice.application.product.command.update.UpdateProductCommand;
import com.turkcell.analyticservice.application.product.command.update.UpdatedProductResponse;
import com.turkcell.analyticservice.application.product.query.getbyid.GetProductByIdItemDto;
import com.turkcell.analyticservice.application.product.query.getbyid.GetProductByIdQuery;
import com.turkcell.analyticservice.application.product.query.list.GetListProductItemDto;
import com.turkcell.analyticservice.application.product.query.list.GetListProductQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final Pipeline pipeline;

    @PostMapping
    public ResponseEntity<CreatedProductResponse> createProduct(@RequestBody CreateProductCommand command) {
        return ResponseEntity.ok(pipeline.send(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdatedProductResponse> updateProduct(
            @PathVariable UUID id, @RequestBody UpdateProductCommand command) {
        command.setId(id);
        return ResponseEntity.ok(pipeline.send(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedProductResponse> deleteProduct(@PathVariable UUID id) {
        return ResponseEntity.ok(pipeline.send(new DeleteProductCommand(id)));
    }
    @GetMapping
    public ResponseEntity<List<GetListProductItemDto>> getListProducts() {
        GetListProductQuery query = new GetListProductQuery();
        List<GetListProductItemDto> products = pipeline.send(query);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetProductByIdItemDto> getProductById(@PathVariable UUID id) {
        GetProductByIdQuery query = new GetProductByIdQuery(id);
        GetProductByIdItemDto product = pipeline.send(query);
        return ResponseEntity.ok(product);
    }
}
