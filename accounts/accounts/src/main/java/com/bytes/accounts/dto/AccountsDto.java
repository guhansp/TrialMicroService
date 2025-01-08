package com.bytes.accounts.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;

@Data
public class AccountsDto {

    @NotEmpty(message = "Account Number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Account Number must be 10 Digits")
    private long accountNumber;
    @NotEmpty(message = "account Type cannot be empty")
    private String accountType;
    @NotEmpty(message = "branch Address cannot be empty")
    private String branchAddress;
}
