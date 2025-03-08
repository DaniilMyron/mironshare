package com.miron.profileservice.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miron.profileservice.domain.api.AccountService;
import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.infrastructure.config.DomainBeansCreationConfig;
import com.miron.profileservice.infrastructure.controller.ProfileController;
import com.miron.profileservice.infrastructure.repo.entity.AccountEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(ProfileController.class)
@Import(DomainBeansCreationConfig.class)
public class AccountBeanInjectedServiceTests{
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String BASE_URL = "/api/v1/profile";

    @Autowired
    private AccountService<Account> accountService;
    @Autowired
    private AccountRepository<Account> accountRepository;
    private Account account;

    @BeforeEach
    void setUp() {
        account = accountService.createAccount("MIRON1", "12345678910", "danya");
        accountService.createAccount("USERNAME", "12345678910", "not danya");

        accountService.subscribeOnUserByUsername("USERNAME", account);
        AccountEntity a = new AccountEntity();

    }

    @Test
    void testGetAccountByUsername() throws Exception {
        System.out.println(account);
        System.out.println(accountRepository.findByUsername("MIRON1").orElseThrow().getPassword());
        assertThat(accountService.retrieveUser(accountRepository.findByUsername("MIRON1").orElseThrow().getId())).isEqualTo(account);
    }

}
