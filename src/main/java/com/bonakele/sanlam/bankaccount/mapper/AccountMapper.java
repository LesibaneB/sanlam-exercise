package com.bonakele.sanlam.bankaccount.mapper;

import com.bonakele.sanlam.bankaccount.dto.BankAccountWithdrawalResponse;
import com.bonakele.sanlam.bankaccount.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    BankAccountWithdrawalResponse bankAccountWithdrawalResponseFromAccount(Account account);
}
