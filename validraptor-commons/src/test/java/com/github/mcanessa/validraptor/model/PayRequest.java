package com.github.mcanessa.validraptor.model;

import java.math.BigDecimal;

public class PayRequest {

    private BigDecimal amount;

    public PayRequest(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
