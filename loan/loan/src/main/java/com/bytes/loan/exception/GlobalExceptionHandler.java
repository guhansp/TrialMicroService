package com.bytes.loan.exception;


import com.bytes.loan.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
    ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.NOT_FOUND,exception.getMessage(), LocalDateTime.now());
    return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
  }


  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception, WebRequest webRequest){
    ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage(), LocalDateTime.now());
    return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(LoanAlreadyExistException.class)
  public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistException(LoanAlreadyExistException exception, WebRequest webRequest){
    ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.BAD_REQUEST,exception.getMessage(), LocalDateTime.now());
    return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
  }




}
