package com.github.mcanessa.validraptor.validator;

import com.github.mcanessa.validraptor.Validator;
import com.github.mcanessa.validraptor.validator.utils.ValidationUtils;

public class CountryValidator implements Validator<String> {

    @Override
    public void validate(String target) {
        ValidationUtils.validateCountry(target);
    }

}
