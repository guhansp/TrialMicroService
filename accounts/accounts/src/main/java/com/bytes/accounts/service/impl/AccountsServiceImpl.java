package com.bytes.accounts.service.impl;


import com.bytes.accounts.dto.AccountsDto;
import com.bytes.accounts.exception.CustomerAlreadyExistsException;
import com.bytes.accounts.constants.AccountsConstants;
import com.bytes.accounts.dto.CustomerDto;
import com.bytes.accounts.entities.Accounts;
import com.bytes.accounts.entities.Customer;
import com.bytes.accounts.exception.ResourceNotFoundException;
import com.bytes.accounts.mapper.AccountsMapper;
import com.bytes.accounts.mapper.CustomerMapper;
import com.bytes.accounts.repository.AccountsRepository;
import com.bytes.accounts.repository.CustomerRepository;
import com.bytes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer=customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with Given Mobile Number : " + customerDto.getMobileNumber());
        }

        Customer savedCustomer=customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }



    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(9000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);

        return newAccount;

    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account","mobileNumber",customer.getCustomerId().toString()));

        CustomerDto customerDto= CustomerMapper.mapsToCustomerDto(customer,new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapsToAccountsDto(accounts,new AccountsDto()));
        return customerDto;


    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsdto = customerDto.getAccountsDto();
        Accounts accounts = null;
        if (accountsdto != null) {
            accounts = accountsRepository.findById(accountsdto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", Long.toString(accountsdto.getAccountNumber())));
            accounts = AccountsMapper.maptoAccounts(accountsdto, accounts);
            accountsRepository.save(accounts);

            Long cusomterId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(cusomterId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer","id",Long.toString(cusomterId))
            );
            customer = CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;





    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
