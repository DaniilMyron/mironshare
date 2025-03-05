package com.miron.profileservice.domain.usecases.impl;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.domain.usecases.RetrieveUser;

import java.util.UUID;

public class RetrieveUserUseCase implements RetrieveUser {
    private final AccountRepository accountRepository;

    public RetrieveUserUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account execute(UUID id) {
        return accountRepository.findById(id).orElse(null);
    }
}
