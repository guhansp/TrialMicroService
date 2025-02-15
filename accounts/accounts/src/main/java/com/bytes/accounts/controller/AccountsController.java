package com.bytes.accounts.controller;


import com.bytes.accounts.constants.AccountsConstants;
import com.bytes.accounts.dto.CustomerDto;
import com.bytes.accounts.dto.ResponseDto;
import com.bytes.accounts.service.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping(path="/api",produces = {MediaType.APPLICATION_JSON_VALUE})

public class AccountsController {

    private IAccountsService iAccountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
   public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                              @Pattern(regexp = "(^$|[0-9]{10})",message = "Account Number must be 10 Digits")
                                                              String mobileNumber)
   {
       System.out.println(mobileNumber);
       CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
       return ResponseEntity
               .status(HttpStatus.OK)
               .body(customerDto);

   }

   @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated =iAccountsService.updateAccount(customerDto);
        if(isUpdated)
        {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }
        else
        {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_UPDATE));
        }
   }

   @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                        @Pattern(regexp = "(^$|[0-9]{10})",message = "Account Number must be 10 Digits")
                                                        String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
       if(isDeleted)
       {
           return ResponseEntity
                   .status(HttpStatus.OK)
                   .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
       }
       else
       {
           return ResponseEntity
                   .status(HttpStatus.EXPECTATION_FAILED)
                   .body(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_DELETE));
       }
   }




}
