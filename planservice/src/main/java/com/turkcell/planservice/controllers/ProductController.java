package com.turkcell.planservice.controllers;

import com.turkcell.planservice.dtos.productdtos.requests.CreateProductRequest;
import com.turkcell.planservice.dtos.productdtos.requests.UpdateProductRequest;
import com.turkcell.planservice.dtos.productdtos.responses.ProductResponse;
import com.turkcell.planservice.entities.Product;
import com.turkcell.planservice.services.abstracts.ProductService;
import com.turkcell.planservice.util.GenericResponse;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @GetMapping
    public GetListResponse<ProductResponse> getAllProducts(@RequestParam int page, @RequestParam int size) {

        return productService.getAllProducts(new PageInfo(page, size));
    }
    @GetMapping("/getOneProduct")
    public ProductResponse getOneProduct(@RequestParam("id") UUID id) {
        return productService.getOneProduct(id);
    }

    @PostMapping
    public GenericResponse<String> addProduct(@RequestBody @Valid CreateProductRequest createProductRequest) {
        productService.createProduct(createProductRequest);
        return GenericResponse.success("generic.product.created");
    }

    @PutMapping
    public GenericResponse<String> updateProduct(@RequestParam UUID id,@RequestBody @Valid UpdateProductRequest updateProductRequest){
        productService.updateProduct(id, updateProductRequest);
        return GenericResponse.success("generic.product.updated");
    }

    @DeleteMapping
    public GenericResponse<String> deleteProduct(@RequestParam UUID id) {
        productService.deleteById(id);
        return GenericResponse.success("generic.product.deleted");
    }
}
