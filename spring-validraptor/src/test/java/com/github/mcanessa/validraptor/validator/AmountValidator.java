package com.github.mcanessa.validraptor.validator;

import com.github.mcanessa.validraptor.Validator;
import com.github.mcanessa.validraptor.validator.utils.ValidationUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AmountValidator implements Validator<BigDecimal> {

    @Override
    public void validate(BigDecimal target) {
        ValidationUtils.validateAmount(target);
    }

}
