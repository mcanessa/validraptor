package com.github.mcanessa.validraptor.aspect;

import com.github.mcanessa.validraptor.Validator;
import com.github.mcanessa.validraptor.annotation.Validate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Aspect
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class ValidationAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Map<Class<? extends Validator>, Validator> validatorsMap;

    public ValidationAspect(Map<Class<? extends Validator>, Validator> validatorsMap) {
        this.validatorsMap = validatorsMap;
    }

    @AfterReturning(pointcut = "execution(@com.github.mcanessa.validraptor.annotation.Validate * *.*(..))",
            returning = "returnValue")
    public void validateReturn(JoinPoint joinPoint, Object returnValue) {
        Validate annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Validate.class);
        getValidator(annotation.value()).validate(returnValue);
    }

    @Before("execution(public * *(.., @com.github.mcanessa.validraptor.annotation.Validate (*), ..))")
    public void validateArgs(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Parameter[] params = signature.getMethod().getParameters();

        Map<Integer, Class<? extends Validator>> paramIndexesMap = getParamIndexValidatorMap(params);

        paramIndexesMap.entrySet().stream()
                .forEach(entry -> getValidator(entry.getValue()).validate(args[entry.getKey()]));
    }

    private Map<Integer, Class<? extends Validator>> getParamIndexValidatorMap(Parameter[] params) {
        Map<Integer, Class<? extends Validator>> result = new HashMap<>();
        Validate annotation = null;
        for (int i = 0; i < params.length; i++) {
            annotation = params[i].getAnnotation(Validate.class);
            if (annotation != null)
                result.put(i, annotation.value());
        }
        return result;
    }

    protected abstract Validator getValidator(Class<? extends Validator> validatorClass);
}
