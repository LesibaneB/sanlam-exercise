package com.bonakele.sanlam.bankaccount.exception;

import java.io.Serial;

public class InsufficientFundsForWithdrawalException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InsufficientFundsForWithdrawalException(String msg) {
        super(msg);
    }
}
