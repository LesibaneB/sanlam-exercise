package com.bonakele.sanlam.bankaccount.exception;

import com.bonakele.sanlam.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class BankAccountControllerExceptionHandler {

    @ExceptionHandler(InsufficientFundsForWithdrawalException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage insufficientFundsForWithdrawalException(InsufficientFundsForWithdrawalException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }
}
