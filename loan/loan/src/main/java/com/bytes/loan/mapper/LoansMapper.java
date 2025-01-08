package com.bytes.loan.mapper;

import com.bytes.loan.dto.LoansDto;
import com.bytes.loan.entity.Loans;

public class LoansMapper {
    public static LoansDto mapstoLoansDto(Loans loans, LoansDto loansDto)
    {

        loansDto.setLoanType(loans.getLoanType());
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setMobileNumber(loans.getMobileNumber());
        return loansDto;

    }
    public static Loans mapstoLoans (LoansDto loansDto, Loans loans)
    {

        loans.setLoanType(loansDto.getLoanType());
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setMobileNumber(loansDto.getMobileNumber());
        return loans;
    }
}
