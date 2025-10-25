package ir.snapp.bimeh.expensetracker.common.application.aspect;

import ir.snapp.bimeh.expensetracker.user.domain.User;
import ir.snapp.bimeh.expensetracker.user.infrastructure.security.AuthenticatedUserProvider;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.file.AccessDeniedException;

@Aspect
@Component
@RequiredArgsConstructor
public class UserAspect {

    private final AuthenticatedUserProvider authenticatedUserProvider;

    @Around("execution(* ir.snapp.bimeh.expensetracker..*Handler.*(..)) && execution(* *(.., @CurrentUser (*), ..))")
    public Object injectCurrentUser(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(CurrentUser.class)) {
                args[i] = authenticatedUserProvider.getCurrentUser();
            }
        }

        return joinPoint.proceed(args);
    }

}
