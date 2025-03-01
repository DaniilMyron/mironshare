package com.miron.profileservice.domain.usecases.impl;

import com.miron.profileservice.domain.usecases.CreateAccount;
import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.AccountRepository;

public class CreateAccountUseCase implements CreateAccount {
    private final AccountRepository accountRepository;

    public CreateAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account execute(String name, String password) {
        var account = new Account(name, password);
        return accountRepository.save(account);
    }
}
