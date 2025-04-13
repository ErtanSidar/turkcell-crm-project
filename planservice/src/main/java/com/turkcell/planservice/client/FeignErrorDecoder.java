package com.turkcell.planservice.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import io.github.ertansidar.exception.detail.ProblemDetails;
import io.github.ertansidar.exception.type.BadRequestException;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.exception.type.InternalServerException;
import io.github.ertansidar.exception.type.ResourceNotFoundException;

import java.io.IOException;

public class FeignErrorDecoder implements ErrorDecoder{

    private final ErrorDecoder errorDecoder = new ErrorDecoder.Default();
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public Exception decode(String methodKey, Response response) {
        ProblemDetails problemDetails;
        try {
            String body = new String(response.body().asInputStream().readAllBytes());

            problemDetails = objectMapper.readValue(body, ProblemDetails.class);

        } catch (IOException e) {
            return new RuntimeException("Feign hatası parse edilemedi", e);
        }

        String reason = problemDetails.getDetail();

        switch (response.status()) {
            case 400:
                throw new BadRequestException(reason);
            case 404:
                throw new BusinessException(reason);
            case 403:
                throw new ResourceNotFoundException(reason);
            case 500:
                throw new InternalServerException(reason);
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}
