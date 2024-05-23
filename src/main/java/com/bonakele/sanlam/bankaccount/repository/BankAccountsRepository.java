package com.bonakele.sanlam.bankaccount.repository;

import com.bonakele.sanlam.bankaccount.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountsRepository extends JpaRepository<Account, Long> {
}
