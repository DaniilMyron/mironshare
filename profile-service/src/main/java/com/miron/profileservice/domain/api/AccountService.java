package com.miron.profileservice.domain.api;

import com.miron.profileservice.domain.entity.Account;

import java.util.List;
import java.util.UUID;


public interface AccountService {
    Account retrieveUser(UUID id);
    List<Account> retrieveUsers(String[] usersId);
    Account createAccount(String username, String password, String name);
    Account changeNameByUsername(String username, String accountName);
    Account changePasswordByUsername(String username, String oldPassword, String newPassword);
    Account subscribeOnUserByUsername(String username, Account userToSubscribeOn);
}
