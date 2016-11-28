package com.loan.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotNull
    private String firstName;

    @NotNull
    private String LastName;

    @NotNull
    private String emailAddress;

    @NotNull
    private String password;

}
