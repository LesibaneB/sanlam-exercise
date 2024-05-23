package com.bonakele.sanlam.bankaccount.service.impl;

import com.bonakele.sanlam.bankaccount.dto.BankAccountWithdrawalResponse;
import com.bonakele.sanlam.bankaccount.entity.Account;
import com.bonakele.sanlam.bankaccount.event.WithdrawalEvent;
import com.bonakele.sanlam.bankaccount.exception.InsufficientFundsForWithdrawalException;
import com.bonakele.sanlam.bankaccount.mapper.AccountMapper;
import com.bonakele.sanlam.bankaccount.repository.BankAccountsRepository;
import com.bonakele.sanlam.bankaccount.service.BankAccountService;
import com.bonakele.sanlam.bankaccount.constants.BankAccountEventStatus;
import com.bonakele.sanlam.bankaccount.constants.EventTopics;
import com.bonakele.sanlam.exception.ResourceNotFoundException;
import com.bonakele.sanlam.service.impl.EventsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountsRepository bankAccountsRepository;
    private final AccountMapper accountMapper;
    private final EventsServiceImpl eventsService;

    @Autowired
    public BankAccountServiceImpl(BankAccountsRepository bankAccountsRepository,
                                  AccountMapper accountMapper,
                                  EventsServiceImpl eventsService) {
        this.bankAccountsRepository = bankAccountsRepository;
        this.accountMapper = accountMapper;
        this.eventsService = eventsService;
    }

    @Override
    public BankAccountWithdrawalResponse withDraw(Long accountId, BigDecimal amount)
            throws ResourceNotFoundException, InsufficientFundsForWithdrawalException {

        Account account = this.bankAccountsRepository
                .findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        // Check current balance
        if (account.getBalance() != null && account.getBalance().compareTo(amount) >= 0) {
            // Update balance
            BigDecimal updatedBalance = account.getBalance().subtract(amount);
            account.setBalance(updatedBalance);
            this.bankAccountsRepository.save(account);

            // After a successful withdrawal, publish a withdrawal event to the event service
            WithdrawalEvent event = new WithdrawalEvent(amount, accountId, BankAccountEventStatus.SUCCESS);
            this.eventsService.publishEvent(event.toJson(), EventTopics.WITHDRAWAL_SUCCESSFUL);

            return accountMapper.bankAccountWithdrawalResponseFromAccount(account);
        } else {
            throw new InsufficientFundsForWithdrawalException("Insufficient funds for withdrawal");
        }
    }
}
