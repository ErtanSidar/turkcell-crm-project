package com.turkcell.analyticservice.application.product.command.delete;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.domain.entity.Product;
import com.turkcell.analyticservice.persistence.product.ProductRepository;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteProductCommand implements Command<DeletedProductResponse> {
    private UUID id;

    @Component
    @RequiredArgsConstructor
    public static class DeleteProductCommandHandler implements Command.Handler<DeleteProductCommand, DeletedProductResponse> {
        private final ProductRepository productRepository;

        @Override
        public DeletedProductResponse handle(DeleteProductCommand command) {
            Product product = productRepository.findById(command.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            productRepository.deleteById(command.getId());
            return new DeletedProductResponse(command.getId(), "Product successfully deleted");
        }
    }
}