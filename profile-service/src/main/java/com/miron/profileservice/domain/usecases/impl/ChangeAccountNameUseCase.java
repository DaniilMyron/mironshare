package com.miron.profileservice.domain.usecases.impl;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.domain.usecases.ChangeAccountName;

import java.util.UUID;

public class ChangeAccountNameUseCase implements ChangeAccountName {
    private final AccountRepository accountRepository;

    public ChangeAccountNameUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account execute(UUID id, String accountName) {
        var account = accountRepository.findById(id).changeAccountName(accountName);
        return accountRepository.save(account);
    }
}
