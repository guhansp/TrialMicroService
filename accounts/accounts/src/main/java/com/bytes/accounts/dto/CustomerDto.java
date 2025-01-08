package com.bytes.accounts.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class CustomerDto {
    @NotEmpty(message = "Name Cannot be null or empty")
    @Size(min = 5,max=30, message = "The length of customer name should be between 5 and 30 characters")
    private String name;
    @NotEmpty(message = "email Cannot be null or empty")
    @Email(message = "email address should be valid")
    private String email;
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 Digits")
    private String mobileNumber;
    private AccountsDto accountsDto;
}
