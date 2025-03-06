package com.miron.profileservice.domain.services;

import com.miron.profileservice.domain.api.AccountService;
import com.miron.profileservice.domain.usecases.*;
import com.miron.profileservice.domain.entity.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AccountUseCasesManagement implements AccountService {
    private final CreateAccount createAccountUseCase;
    private final ChangeAccountName changeAccountNameUseCase;
    private final ChangeAccountPassword changeAccountPasswordUseCase;
    private final SubscribeOnUser subscribeOnUserUseCase;
    private final RetrieveUser retrieveAccountUseCase;

    public AccountUseCasesManagement(CreateAccount createAccountUseCase, ChangeAccountName changeAccountNameUseCase, ChangeAccountPassword changeAccountPasswordUseCase, SubscribeOnUser subscribeOnUserUseCase, RetrieveUser retrieveAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.changeAccountNameUseCase = changeAccountNameUseCase;
        this.changeAccountPasswordUseCase = changeAccountPasswordUseCase;
        this.subscribeOnUserUseCase = subscribeOnUserUseCase;
        this.retrieveAccountUseCase = retrieveAccountUseCase;
    }

    @Override
    public Account retrieveUser(UUID id) {
        return retrieveAccountUseCase.execute(id);
    }

    @Override
    public List<Account> retrieveUsers(String[] usersId) {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < usersId.length; i++) {
            accounts.add(retrieveAccountUseCase.execute(UUID.fromString(usersId[i])));
        }
        return accounts;
    }

    @Override
    public Account createAccount(String username, String password, String name) {
        return createAccountUseCase.execute(username, password, name);
    }

    @Override
    public Account changeNameByUsername(String username, String accountName) {
        return changeAccountNameUseCase.execute(username, accountName);
    }

    @Override
    public Account changePasswordByUsername(String username, String oldPassword, String newPassword) {
        return changeAccountPasswordUseCase.execute(username, oldPassword, newPassword);
    }

    @Override
    public Account subscribeOnUserByUsername(String username, Account userToSubscribeOn) {
        return subscribeOnUserUseCase.execute(username, userToSubscribeOn);
    }
}
