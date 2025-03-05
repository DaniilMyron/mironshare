package com.miron.profileservice.domain.spi;

import com.miron.profileservice.domain.entity.Account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findById(UUID id);
    Optional<Account> findByUsername(String username);
    Optional<Account> findByAccountName(String accountName);
    List<Account> findAll();
    void deleteById(UUID id);
}
