package com.miron.profileservice.domain.services;

import com.miron.profileservice.domain.api.AccountService;
import com.miron.profileservice.domain.usecases.ChangeAccountName;
import com.miron.profileservice.domain.usecases.ChangeAccountPassword;
import com.miron.profileservice.domain.usecases.CreateAccount;
import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.usecases.SubscribeOnUser;


public class AccountUseCasesManagement implements AccountService {
    private final CreateAccount createAccountUseCase;
    private final ChangeAccountName changeAccountNameUseCase;
    private final ChangeAccountPassword changeAccountPasswordUseCase;
    private final SubscribeOnUser subscribeOnUserUseCase;

    public AccountUseCasesManagement(CreateAccount createAccountUseCase, ChangeAccountName changeAccountNameUseCase, ChangeAccountPassword changeAccountPasswordUseCase, SubscribeOnUser subscribeOnUserUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.changeAccountNameUseCase = changeAccountNameUseCase;
        this.changeAccountPasswordUseCase = changeAccountPasswordUseCase;
        this.subscribeOnUserUseCase = subscribeOnUserUseCase;
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
