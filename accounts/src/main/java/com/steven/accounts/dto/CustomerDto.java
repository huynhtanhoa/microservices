package com.steven.accounts.dto;

import lombok.*;

@Data
public class CustomerDto {

    private String name;

    private String email;

    private String mobileNumber;

    // use to attach account details when return the response
    private AccountsDto accountsDto;

}
