package com.github.mcanessa.validraptor.service;

import com.github.mcanessa.validraptor.model.PayRequest;

import java.math.BigDecimal;

public interface PayService {

    public String payWithRequest(PayRequest request);

    public String payAndValidateResponse(PayRequest request);

    public String payWithParameters(String country, BigDecimal amount, String cardNumber);
}
