package com.miron.profileservice.domain.usecases.impl;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.domain.usecases.ChangeAccountName;


public class ChangeAccountNameUseCase implements ChangeAccountName {
    private final AccountRepository accountRepository;

    public ChangeAccountNameUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account execute(String username, String accountName) {
        var account = accountRepository.findByUsername(username)
                .orElseThrow(IllegalArgumentException::new)
                .changeAccountName(accountName);
        return accountRepository.save(account);
    }
}
