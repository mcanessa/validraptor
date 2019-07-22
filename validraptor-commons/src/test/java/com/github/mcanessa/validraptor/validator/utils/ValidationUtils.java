package com.github.mcanessa.validraptor.validator.utils;

import com.github.mcanessa.validraptor.model.PayRequest;
import com.github.mcanessa.validraptor.validator.exception.ValidationException;

import java.math.BigDecimal;

public class ValidationUtils {

    public static void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 1)
            throw new ValidationException("Invalid amount! Must be greater than zero!");
    }

    public static void validateCard(String target) {
        if (target == null || target.isEmpty() || !target.startsWith("4"))
            throw new ValidationException("Invalid card!, Only Visa allowed!");
    }

    public static void validateCountry(String target) {
        if (target == null || target.isEmpty() || !target.equals("ARG"))
            throw new ValidationException("Invalid country!");
    }

    public static void validateRequest(PayRequest target) {
        if (target.getAmount().compareTo(BigDecimal.ZERO) == -1)
            throw new ValidationException("Noob");
    }

    public static void validateResponse(String target) {
        if (target == null || target.isEmpty())
            throw new ValidationException("The response can't be null!");
    }
}
