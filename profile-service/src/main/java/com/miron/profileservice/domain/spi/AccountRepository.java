package com.miron.profileservice.domain.spi;

import com.miron.profileservice.domain.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountRepository {
    Account save(Account account);
    Account findById(UUID id);
    List<Account> findAll();
    void deleteById(UUID id);
}
