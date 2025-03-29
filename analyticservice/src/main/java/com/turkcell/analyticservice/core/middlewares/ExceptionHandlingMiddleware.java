package com.turkcell.analyticservice.core.middlewares;


import an.awesome.pipelinr.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
public class ExceptionHandlingMiddleware implements Command.Middleware {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingMiddleware.class);

    @Override
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        try {
            return next.invoke();
        } catch (Exception e) {
            logger.error("Error while handling command {}: {}", command.getClass().getSimpleName(), e.getMessage(), e);
            throw e;
        }
    }
}