package com.turkcell.analyticservice.application.product.mapper;

import com.turkcell.analyticservice.application.product.command.create.CreateProductCommand;
import com.turkcell.analyticservice.application.product.command.create.CreatedProductResponse;
import com.turkcell.analyticservice.application.product.command.update.UpdateProductCommand;
import com.turkcell.analyticservice.application.product.command.update.UpdatedProductResponse;
import com.turkcell.analyticservice.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);


    Product createProductFromCreateCommand(CreateProductCommand command);
    CreatedProductResponse createProductResponseFromProduct(Product product);


    @Mapping(target = "id", ignore = true)
    void updateProductFromUpdateCommand(UpdateProductCommand command, @MappingTarget Product product);
    UpdatedProductResponse createUpdatedProductResponse(Product product);
}