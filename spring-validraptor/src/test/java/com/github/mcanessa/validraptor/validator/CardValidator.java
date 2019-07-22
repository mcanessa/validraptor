package com.github.mcanessa.validraptor.validator;

import com.github.mcanessa.validraptor.Validator;
import com.github.mcanessa.validraptor.validator.utils.ValidationUtils;
import org.springframework.stereotype.Component;

@Component
public class CardValidator implements Validator<String> {

    @Override
    public void validate(String target) {
        ValidationUtils.validateCard(target);
    }

}
