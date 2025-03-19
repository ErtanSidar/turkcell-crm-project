package com.turkcell.planservice.mappers;

import com.turkcell.planservice.dtos.productdtos.requests.CreateProductRequest;
import com.turkcell.planservice.dtos.productdtos.requests.UpdateProductRequest;
import com.turkcell.planservice.dtos.productdtos.responses.ProductResponse;
import com.turkcell.planservice.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResponse createProductResponseFromProduct(Product product);

    @Mapping(target = "id", ignore = true)
    Product createProductFromCreateProductRequest(CreateProductRequest request);

    void updateProductFromRequest(UpdateProductRequest request, @MappingTarget Product product);

    Product createProductFromProductResponse(ProductResponse productResponse);
}
