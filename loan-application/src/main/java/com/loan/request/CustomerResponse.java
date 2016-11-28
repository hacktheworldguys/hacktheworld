package com.loan.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    @NotNull
    private String message;

    @NotNull
    private long customerId;

    @NotNull
    private String email;
}
