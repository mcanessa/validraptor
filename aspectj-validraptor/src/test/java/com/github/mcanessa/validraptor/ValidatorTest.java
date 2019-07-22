package com.github.mcanessa.validraptor;

import com.github.mcanessa.validraptor.model.PayRequest;
import com.github.mcanessa.validraptor.service.PayService;
import com.github.mcanessa.validraptor.validator.exception.ValidationException;
import com.github.mcanessa.validraptor.service.impl.PayServiceImpl;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ValidatorTest {

    private PayService service = new PayServiceImpl();

    @Test(expected = ValidationException.class)
    public void argumentFailsValidationTest() {
        service.payWithRequest(new PayRequest(new BigDecimal("-99")));
    }

    @Test
    public void argumentPassesValidationTest() {
        String response = service.payWithRequest(new PayRequest(new BigDecimal("99")));
        Assert.assertEquals("OK", response);
    }

    @Test(expected = ValidationException.class)
    public void outputFailsValidationTest() {
        service.payAndValidateResponse(null);
    }

    @Test
    public void outputPassesValidationTest() {
        String response = service.payAndValidateResponse(new PayRequest(new BigDecimal("99")));
        Assert.assertEquals("OK", response);
    }

    @Test
    public void allValidatorPassTest() {
        String response = service.payWithParameters("ARG", new BigDecimal("99"), "420798");
        Assert.assertEquals("OK", response);
    }

    @Test(expected = ValidationException.class)
    public void amountFailsTest() {
        String response = service.payWithParameters("ARG", new BigDecimal("0"), "420798");
        Assert.assertEquals("OK", response);
    }

    @Test(expected = ValidationException.class)
    public void cardFailsTest() {
        String response = service.payWithParameters("ARG", new BigDecimal("1200"), "54322");
        Assert.assertEquals("OK", response);
    }

    @Test(expected = ValidationException.class)
    public void parameterValidationPassesAndReturnValidationFailsTest() {
        String response = service.payWithParameters("ARG", new BigDecimal("99"), "4242");
        Assert.assertEquals("OK", response);
    }

}
