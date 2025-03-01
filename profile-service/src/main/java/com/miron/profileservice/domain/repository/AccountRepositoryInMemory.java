package com.miron.profileservice.domain.repository;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.AccountRepository;

import java.util.*;

public class AccountRepositoryInMemory implements AccountRepository {
    private final Map<UUID, Account> accounts = new HashMap<>();

    @Override
    public Account save(Account account) {
        accounts.computeIfPresent(account.getId(), (key, value) -> value.changeAccountName(account.getName())
                .changeAccountPassword(value.getPassword(), account.getPassword())
                .setAdditionalInformation(account.getAdditionalInformation())
        );
        accounts.putIfAbsent(account.getId(), account);
        return accounts.get(account.getId());
    }

    @Override
    public Account findById(UUID id) {
        return accounts.get(id);
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public void deleteById(UUID id) {
        accounts.remove(id);
    }
}
