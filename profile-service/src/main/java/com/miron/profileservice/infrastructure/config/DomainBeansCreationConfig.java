package com.miron.profileservice.infrastructure.config;

import com.miron.profileservice.domain.api.AccountService;
import com.miron.profileservice.domain.repository.AccountRepositoryInMemory;
import com.miron.profileservice.domain.services.AccountUseCasesManagement;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.domain.usecases.*;
import com.miron.profileservice.domain.usecases.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainBeansCreationConfig {
    @Bean
    public AccountRepository accountRepository() {
        return new AccountRepositoryInMemory();
    }

    @Bean
    public RetrieveUser retrieveAccount(AccountRepository accountRepository) {
        return new RetrieveUserUseCase(accountRepository);
    }

    @Bean
    public CreateAccount createAccount(AccountRepository accountRepository) {
        return new CreateAccountUseCase(accountRepository);
    }

    @Bean
    public ChangeAccountName changeAccountName(AccountRepository accountRepository) {
        return new ChangeAccountNameUseCase(accountRepository);
    }

    @Bean
    public ChangeAccountPassword changeAccountPassword(AccountRepository accountRepository) {
        return new ChangeAccountPasswordUseCase(accountRepository);
    }

    @Bean
    public SubscribeOnUser subscribeOnUser(AccountRepository accountRepository) {
        return new SubscribeOnUserUseCase(accountRepository);
    }

    @Bean
    public AccountService accountService(CreateAccount createAccount,
                                         ChangeAccountName changeAccountName,
                                         ChangeAccountPassword changeAccountPassword,
                                         SubscribeOnUser subscribeOnUser,
                                         RetrieveUser retrieveAccount) {
        return new AccountUseCasesManagement(createAccount, changeAccountName, changeAccountPassword, subscribeOnUser, retrieveAccount);
    }
}
