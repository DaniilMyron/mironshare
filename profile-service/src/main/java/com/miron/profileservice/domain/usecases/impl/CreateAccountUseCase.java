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
    public Account execute(String username, String password, String name) {
        if(accountRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Account with username " + username + " already exists");
        }
        var account = new Account(username, password, name);
        return accountRepository.save(account);
    }
}
