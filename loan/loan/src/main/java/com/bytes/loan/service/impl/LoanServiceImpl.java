package com.bytes.loan.service.impl;

import com.bytes.loan.constants.LoansConstants;
import com.bytes.loan.dto.LoansDto;
import com.bytes.loan.entity.Loans;
import com.bytes.loan.exception.LoanAlreadyExistException;
import com.bytes.loan.exception.ResourceNotFoundException;
import com.bytes.loan.mapper.LoansMapper;
import com.bytes.loan.repository.LoanRepository;
import com.bytes.loan.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {
    private LoanRepository loanRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> loan = loanRepository.findByMobileNumber(mobileNumber);
        if (loan.isPresent()) {
            throw new LoanAlreadyExistException("Loan already exist with mobile number " + mobileNumber);
        }

        Loans loan2 = createNewLoan(mobileNumber);
        System.out.println(loan2);

        loanRepository.save(loan2);
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan","mobileNumber",mobileNumber));
        return LoansMapper.mapstoLoansDto(loan,new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loan = LoansMapper.mapstoLoans(loansDto,new Loans());
        Loans mappedLoan = loanRepository.findByLoanNumber(loan.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan","Loan Number",loan.getLoanNumber()));
        loanRepository.save(LoansMapper.mapstoLoans(loansDto,mappedLoan));
        return true;


    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan","mobileNumber",mobileNumber));
        loanRepository.delete(loan);
        return true;
    }
}
