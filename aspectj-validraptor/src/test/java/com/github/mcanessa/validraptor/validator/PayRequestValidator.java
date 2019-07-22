package com.github.mcanessa.validraptor.validator;

import com.github.mcanessa.validraptor.Validator;
import com.github.mcanessa.validraptor.model.PayRequest;
import com.github.mcanessa.validraptor.validator.utils.ValidationUtils;

public class PayRequestValidator implements Validator<PayRequest> {

    @Override
    public void validate(PayRequest target) {
        ValidationUtils.validateRequest(target);
    }

}
