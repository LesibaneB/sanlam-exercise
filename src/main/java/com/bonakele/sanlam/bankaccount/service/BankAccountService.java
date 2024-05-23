package com.bonakele.sanlam.bankaccount.service;

import com.bonakele.sanlam.bankaccount.dto.BankAccountWithdrawalResponse;
import com.bonakele.sanlam.bankaccount.exception.InsufficientFundsForWithdrawalException;
import com.bonakele.sanlam.exception.ResourceNotFoundException;

import java.math.BigDecimal;

public interface BankAccountService {
    public BankAccountWithdrawalResponse withDraw(Long accountId, BigDecimal amount) throws ResourceNotFoundException, InsufficientFundsForWithdrawalException;
}
