package com.bytes.accounts.mapper;

import com.bytes.accounts.dto.AccountsDto;
import com.bytes.accounts.dto.CustomerDto;
import com.bytes.accounts.entities.Accounts;
import com.bytes.accounts.entities.Customer;

public class CustomerMapper {

    public static CustomerDto mapsToCustomerDto(Customer customer, CustomerDto customerDto)
    {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer)
    {
     customer.setName(customerDto.getName());
     customer.setEmail(customerDto.getEmail());
     customer.setMobileNumber(customerDto.getMobileNumber());
     return customer;
    }
}
