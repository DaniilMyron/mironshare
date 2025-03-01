package com.miron.profileservice.domain.usecases.impl;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.domain.usecases.SubscribeOnUser;


public class SubscribeOnUserUseCase implements SubscribeOnUser {
    private final AccountRepository accountRepository;

    public SubscribeOnUserUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account execute(String username, Account userToSubscribeOn) {
        if(userToSubscribeOn.getUsername().equals(username)) {
            throw new IllegalArgumentException("Cannot subscribe to yourself");
        }
        var user = accountRepository.findByUsername(username)
                .orElseThrow(IllegalArgumentException::new)
                .subscribeOnUser(userToSubscribeOn);
        var userToSubscribeOnRepository = accountRepository.findById(userToSubscribeOn.getId())
                .orElseThrow(IllegalArgumentException::new);
        accountRepository.save(userToSubscribeOnRepository);
        return accountRepository.save(user);
    }
}
