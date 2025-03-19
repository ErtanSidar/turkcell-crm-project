package com.turkcell.analyticservice.application.product.query.getbyid;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.domain.entity.Product;
import com.turkcell.analyticservice.persistence.product.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GetProductByIdQuery implements Command<GetProductByIdItemDto> {
    private final UUID id;

    @Component
    @RequiredArgsConstructor
    public static class GetProductByIdQueryHandler
            implements Command.Handler<GetProductByIdQuery, GetProductByIdItemDto> {

        private final ProductRepository productRepository;

        @Override
        public GetProductByIdItemDto handle(GetProductByIdQuery query) {
            Product product = productRepository.findById(query.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + query.getId()));

            return new GetProductByIdItemDto(product.getId(), product.getProductName(),
                    product.getDescription(), product.getProductType());
        }
    }
}