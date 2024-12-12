package com.steven.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name can not be null or empty")
    @Size(min=5, max=30, message = "The length of customer name shoud be between 5 and 30")
    private String name;

    @NotEmpty(message = "Email address can not be null or empty")
    @Email(message= "Email address should be valid value")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
    private String mobileNumber;

    // use to attach account details when return the response
    private AccountsDto accountsDto;

}
