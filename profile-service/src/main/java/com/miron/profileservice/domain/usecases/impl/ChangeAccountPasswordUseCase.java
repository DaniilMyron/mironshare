package com.miron.profileservice.domain.usecases.impl;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.domain.usecases.ChangeAccountPassword;

public class ChangeAccountPasswordUseCase implements ChangeAccountPassword {
    private final AccountRepository accountRepository;

    public ChangeAccountPasswordUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account execute(String username, String oldPassword, String newPassword) {
        var account = accountRepository.findByUsername(username).orElseThrow(IllegalArgumentException::new);
        return accountRepository.save(account.changeAccountPassword(oldPassword, newPassword));
    }
}
