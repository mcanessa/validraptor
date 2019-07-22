package com.github.mcanessa.validraptor.service.impl;

import java.math.BigDecimal;

import com.github.mcanessa.validraptor.annotation.Validate;
import com.github.mcanessa.validraptor.model.PayRequest;
import com.github.mcanessa.validraptor.service.PayService;
import com.github.mcanessa.validraptor.validator.AmountValidator;
import com.github.mcanessa.validraptor.validator.CardValidator;
import com.github.mcanessa.validraptor.validator.CountryValidator;
import com.github.mcanessa.validraptor.validator.PayRequestValidator;
import com.github.mcanessa.validraptor.validator.ResponseValidator;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {

    @Override
    public String payWithRequest(@Validate(PayRequestValidator.class) PayRequest request) {
        return "OK";
    }

    @Override
    @Validate(ResponseValidator.class)
    public String payAndValidateResponse(PayRequest request) {
        if (request == null)
            return null;
        return "OK";
    }

    @Override
    @Validate(ResponseValidator.class)
    public String payWithParameters(@Validate(CountryValidator.class) String country,
            @Validate(AmountValidator.class) BigDecimal amount,
            @Validate(CardValidator.class) String cardNumber) {
        if (cardNumber.equals("4242"))
            return null;
        return "OK";
    }
}
