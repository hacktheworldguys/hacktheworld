package com.loan.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest {

    @NotNull
    private BigDecimal amount;

    @NotNull
    private int term;

    @NotNull
    private long customerId;

    private String ipAddress;
}
