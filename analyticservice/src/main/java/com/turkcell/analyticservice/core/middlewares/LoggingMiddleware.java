//package com.turkcell.analyticservice.core.middlewares;
//
//import an.awesome.pipelinr.Command;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import io.github.ertansidar.audit.AuditAwareImpl;
//
//@Component
//@Order(1)
//public class LoggingMiddleware implements Command.Middleware {
//
//    private static final Logger logger = LoggerFactory.getLogger(LoggingMiddleware.class);
//    private final AuditAwareImpl auditAware;
//
//
//    public LoggingMiddleware(AuditAwareImpl auditAware) {
//        this.auditAware = auditAware;
//    }
//
//    @Override
//    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
//        String username = auditAware.getCurrentAuditor().orElse("SYSTEM");
//
//        logger.info("[{}] is handling command: {}", username, command.getClass().getSimpleName());
//
//        R response = next.invoke();
//
//        logger.info("[{}] has handled command: {}", username, command.getClass().getSimpleName());
//
//        return response;
//    }
//}
