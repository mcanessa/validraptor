package com.github.mcanessa.validraptor.aspect;

import com.github.mcanessa.validraptor.Validator;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Aspect
@Component
public class ValidationSpringAspect extends ValidationAspect {

    @Autowired
    public ValidationSpringAspect(List<Validator> validators) {
        super(validators.stream().collect(Collectors.toMap(Validator::getClass, v -> v)));
    }

    @Override
    protected Validator getValidator(Class<? extends Validator> validatorClass) {
        return Optional.ofNullable(validatorsMap.get(validatorClass))
                .orElseThrow(() -> new IllegalArgumentException(String.format(
                        "The class %s is not a known Validator. It must be a spring bean!", validatorClass.getName())));
    }
}
