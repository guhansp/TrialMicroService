package com.bytes.accounts.mapper;

import com.bytes.accounts.dto.AccountsDto;
import com.bytes.accounts.entities.Accounts;


public class AccountsMapper {

    public static AccountsDto mapsToAccountsDto(Accounts accounts, AccountsDto accountsDto)
    {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        accountsDto.setAccountType(accounts.getAccountType());
        return accountsDto;
    }

    public static Accounts maptoAccounts(AccountsDto accountsDto, Accounts accounts)
    {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        accounts.setAccountType(accountsDto.getAccountType());
        return accounts;
    }
}
