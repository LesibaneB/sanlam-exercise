package com.bonakele.sanlam.bankaccount.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class WithdrawalEvent {
    private BigDecimal amount;
    private Long accountId;
    private String status;

    // Convert to JSON String
    public String toJson() {
        return String.format("{\"amount\":\"%s\",\"accountId\":%d,\"status\":\"%s\"}",
                amount, accountId, status);
    }
}
