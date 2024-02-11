package com.awanrpn.invenmanager.aop;

import com.awanrpn.invenmanager.model.exception.UniversalModelExceptionBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionErrorHandling {

    private final UniversalModelExceptionBuilder universalModelExceptionBuilder;

    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void transactionalMethod() {

    }

    @Around("transactionalMethod()")
    public Object createUserAop(ProceedingJoinPoint pjp) {

        Object[] args = pjp.getArgs();
        String methodName = pjp.getSignature().getName();

        try {
            return pjp.proceed(args);
        } catch (Throwable e) {

            if (e instanceof ResponseStatusException) {
                throw (ResponseStatusException) e;
            }

            throw universalModelExceptionBuilder.actionFailed(methodName, e);
        }

    }

}
