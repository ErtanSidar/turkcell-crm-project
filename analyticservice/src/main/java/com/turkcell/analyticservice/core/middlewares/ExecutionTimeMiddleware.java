package com.turkcell.analyticservice.core.middlewares;

import an.awesome.pipelinr.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ExecutionTimeMiddleware implements Command.Middleware {
    private static final Logger logger = LoggerFactory.getLogger(ExecutionTimeMiddleware.class);

    @Override
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        long start = System.currentTimeMillis();
        R response = next.invoke();
        long duration = System.currentTimeMillis() - start;

        logger.info("Execution time for {}: {} ms", command.getClass().getSimpleName(), duration);
        return response;
    }
}