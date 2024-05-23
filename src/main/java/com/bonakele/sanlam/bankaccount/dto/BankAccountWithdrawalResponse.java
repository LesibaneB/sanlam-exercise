package com.bonakele.sanlam.bankaccount.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankAccountWithdrawalResponse {
    private BigDecimal balance;
}
