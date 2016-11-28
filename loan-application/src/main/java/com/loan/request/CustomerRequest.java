package com.loan.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by dogukan.ozturkan on 28.11.2016.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @NotNull
    private String firstName;

    @NotNull
    private String LastName;

    @NotNull
    private String emailAddress;

    @NotNull
    private String birthday;

    @NotNull
    private String mobilePhone;

    @NotNull
    private String address;

}
