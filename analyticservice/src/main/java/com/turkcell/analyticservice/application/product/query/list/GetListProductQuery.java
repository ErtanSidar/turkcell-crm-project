package com.turkcell.analyticservice.application.product.query.list;

import an.awesome.pipelinr.Command;
import com.turkcell.analyticservice.domain.entity.Product;
import com.turkcell.analyticservice.persistence.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

public class GetListProductQuery implements Command<List<GetListProductItemDto>> {

    @Component
    @RequiredArgsConstructor
    public static class GetListProductQueryHandler
            implements Command.Handler<GetListProductQuery, List<GetListProductItemDto>> {

        private final ProductRepository productRepository;

        @Override
        public List<GetListProductItemDto> handle(GetListProductQuery query) {
            List<Product> products = productRepository.findAll();

            return products.stream()
                    .map(prdct -> new GetListProductItemDto(prdct.getId(),prdct.getProductName(),prdct.getDescription(), prdct.getProductType()))
                    .toList();
        }
    }
}