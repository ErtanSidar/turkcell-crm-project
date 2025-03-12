package com.turkcell.analyticservice.application.product.command.create;


import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.application.product.mapper.ProductMapper;
import com.turkcell.analyticservice.domain.entity.Product;
import com.turkcell.analyticservice.persistence.product.ProductRepository;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductCommand implements Command<CreatedProductResponse> {
    private String productName;
    private String description;
    private String productType;

    @Component
    @RequiredArgsConstructor
    public static class CreateProductCommandHandler implements Command.Handler<CreateProductCommand, CreatedProductResponse> {
        private final ProductRepository productRepository;

        @Override
        public CreatedProductResponse handle(CreateProductCommand command) {
            ProductMapper productMapper = ProductMapper.INSTANCE;
            Product product = productMapper.createProductFromCreateCommand(command);
            productRepository.save(product);
            return productMapper.createProductResponseFromProduct(product);
        }
    }
}