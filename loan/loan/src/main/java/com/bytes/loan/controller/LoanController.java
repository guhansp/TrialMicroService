package com.bytes.loan.controller;

import com.bytes.loan.constants.LoansConstants;
import com.bytes.loan.dto.LoansDto;
import com.bytes.loan.dto.ResponseDto;
import com.bytes.loan.service.impl.LoanServiceImpl;
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
public class LoanController {

    public LoanServiceImpl loanService;


    @PostMapping(path="/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam String mobileNumber)
    {
        loanService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201,LoansConstants.MESSAGE_201));

    }

    @GetMapping(path="/fetch")
    public ResponseEntity<LoansDto> fetchLoan (@RequestParam String mobileNumber)
    {

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(loanService.fetchLoan(mobileNumber));

    }

    @PutMapping(path="/update")
    public ResponseEntity<ResponseDto> updateLoan (@RequestBody LoansDto loansDto)
    {
        boolean status = loanService.updateLoan(loansDto);
        if(status)
        {
            return  ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));

        }

        return  ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_UPDATE));




    }

    @DeleteMapping(path="/delete")
    public ResponseEntity<ResponseDto> deleteLoan (@RequestParam String mobileNumber)
    {
        boolean status = loanService.deleteLoan(mobileNumber);
        if(status)
        {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));
        }
        return  ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_DELETE));

    }


}
