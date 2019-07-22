package com.github.mcanessa.validraptor.aspect;

import com.github.mcanessa.validraptor.Validator;
import com.github.mcanessa.validraptor.exception.ValidraptorException;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Optional;

@Aspect
public class ValidationAJAspect extends ValidationAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ValidationAJAspect() {
        super(new HashMap<>());
    }

    protected Validator getValidator(Class<? extends Validator> validatorClass) {
        return Optional.ofNullable(validatorsMap.get(validatorClass)).orElseGet(() -> initValidator(validatorClass));
    }

    private Validator initValidator(Class<? extends Validator> clazz) {
        try {
            Validator validator = clazz.newInstance();
            this.validatorsMap.put(clazz, validator);
            return validator;
        } catch (Exception e) {
            logger.error("Failed to initialize validator!", e);
            throw new ValidraptorException();
        }
    }
}
