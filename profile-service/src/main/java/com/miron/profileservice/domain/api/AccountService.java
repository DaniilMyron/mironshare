package com.miron.profileservice.domain.api;

import com.miron.profileservice.domain.entity.Account;

import java.util.UUID;

public interface AccountService {
    Account createAccount(String name, String password);
    Account changeNameById(UUID id, String accountName);
}
