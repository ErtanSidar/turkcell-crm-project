package com.turkcell.analyticservice.application.product.command.update;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.application.product.mapper.ProductMapper;
import com.turkcell.analyticservice.domain.entity.Product;
import com.turkcell.analyticservice.persistence.product.ProductRepository;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductCommand implements Command<UpdatedProductResponse> {
    private UUID id;
    private String productName;
    private String description;
    private String productType;

    @Component
    @RequiredArgsConstructor
    public static class UpdateProductCommandHandler implements Command.Handler<UpdateProductCommand, UpdatedProductResponse> {
        private final ProductRepository productRepository;

        @Override
        public UpdatedProductResponse handle(UpdateProductCommand command) {
            Product product = productRepository.findById(command.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            ProductMapper productMapper = ProductMapper.INSTANCE;
            productMapper.updateProductFromUpdateCommand(command, product);
            productRepository.save(product);
            return productMapper.createUpdatedProductResponse(product);
        }
    }
}