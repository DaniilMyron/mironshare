package com.miron.profileservice.domain.services;

import com.miron.profileservice.domain.api.AccountService;
import com.miron.profileservice.domain.usecases.ChangeAccountName;
import com.miron.profileservice.domain.usecases.CreateAccount;
import com.miron.profileservice.domain.entity.Account;

import java.util.UUID;

public class AccountUseCasesManagement implements AccountService {
    private final CreateAccount createAccountUseCase;
    private final ChangeAccountName changeAccountNameUseCase;

    public AccountUseCasesManagement(CreateAccount createAccountUseCase, ChangeAccountName changeAccountNameUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.changeAccountNameUseCase = changeAccountNameUseCase;
    }

    @Override
    public Account createAccount(String name, String password) {
        return createAccountUseCase.execute(name, password);
    }

    @Override
    public Account changeNameById(UUID id, String accountName) {
        return changeAccountNameUseCase.execute(id, accountName);
    }
}
