package com.bonakele.sanlam.bankaccount.controller;

import com.bonakele.sanlam.bankaccount.dto.BankAccountWithdrawalResponse;
import com.bonakele.sanlam.bankaccount.service.impl.BankAccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/bank")
public class BankAccountController {

    private final BankAccountServiceImpl bankAccountService;

    public BankAccountController(BankAccountServiceImpl bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/withdraw")
    public ResponseEntity<BankAccountWithdrawalResponse> withdraw(@RequestParam("accountId") Long accountId,
                                                                  @RequestParam("amount") BigDecimal amount) {
        BankAccountWithdrawalResponse response = this.bankAccountService.withDraw(accountId, amount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
