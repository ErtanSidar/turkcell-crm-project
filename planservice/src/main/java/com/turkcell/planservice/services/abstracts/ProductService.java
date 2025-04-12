package com.turkcell.planservice.services.abstracts;

import com.turkcell.planservice.dtos.productdtos.requests.CreateProductRequest;
import com.turkcell.planservice.dtos.productdtos.requests.UpdateProductRequest;
import com.turkcell.planservice.dtos.productdtos.responses.ProductResponse;
import com.turkcell.planservice.entities.Product;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    GetListResponse<ProductResponse> getAllProducts(PageInfo pageInfo);

    void delete(UUID id);

    ProductResponse getOneProduct(UUID id);

    void createProduct(CreateProductRequest createProductRequest);

    void updateProduct(UUID id, UpdateProductRequest updateProductRequest);
}
